package com.arka.cotizador.application.usercase;


import com.arka.cotizador.domain.model.CotizacionRequest;
import com.arka.cotizador.domain.model.CotizacionResponse;
import com.arka.cotizador.domain.model.Product;
import com.arka.cotizador.domain.model.ProductoCotizado;
import com.arka.cotizador.domain.model.ProductoSolicitado;
import com.arka.cotizador.domain.port.in.CotizadorUseCase;
import com.arka.cotizador.domain.service.CalculadoraPrecios;
import com.arka.cotizador.domain.port.out.CotizacionRepositoryPort;
import com.arka.cotizador.domain.port.out.ProductoServicePort;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class CotizadorApplicationService implements CotizadorUseCase {

    private final CotizacionRepositoryPort cotizacionRepository;
    private final ProductoServicePort productoService;
    private final CalculadoraPrecios calculadoraPrecios;
    private final CircuitBreaker circuitBreaker;


    public CotizadorApplicationService(CotizacionRepositoryPort cotizacionRepository,
                                       ProductoServicePort productoService,
                                       CalculadoraPrecios calculadoraPrecios,
                                       CircuitBreakerRegistry circuitBreakerRegistry) {
        this.cotizacionRepository = cotizacionRepository;
        this.productoService = productoService;
        this.calculadoraPrecios = calculadoraPrecios;
        this.circuitBreaker = circuitBreakerRegistry.circuitBreaker("calculoCotizacion");

    }

    @Override
    public Mono<CotizacionResponse> generarCotizacion(CotizacionRequest request) {
        // similar error llamar metodo fallbackCotizacion
        return Mono.fromCallable(() -> {
            if (request.getProductos() == null || request.getProductos().isEmpty()) {
                throw new IllegalArgumentException("La lista de productos no puede estar vacía");
            }
            // Generar ID único para la cotización
            String cotizacionId = UUID.randomUUID().toString();

            // Lista de productos cotizados
            List<ProductoCotizado> productosCotizados = new ArrayList<>();
            BigDecimal subtotal = BigDecimal.ZERO;

            for (ProductoSolicitado productSolicitud : request.getProductos()) {
                if (productSolicitud == null) continue;
                // Obtener detalles del producto desde el servicio externo
                BigDecimal precioUnitario = productSolicitud.getPrecioBase();
                BigDecimal subtotalProducto = precioUnitario.multiply(BigDecimal.valueOf(productSolicitud.getCantidad()));
                BigDecimal precioConDescuento = calculadoraPrecios.calcularPrecioConDescuento(
                        precioUnitario,
                        productSolicitud.getCantidad(),
                        request.getTipoCliente()
                );
                BigDecimal descuento = subtotalProducto.subtract(precioConDescuento);
                BigDecimal precioFinal = precioConDescuento;

                // Construcción del producto cotizado
                ProductoCotizado productoCotizado = new ProductoCotizado(
                        productSolicitud.getProductoId(),
                        productSolicitud.getNombreProducto(),
                        "Descripcion no disponible", // ahora sí lo recibes del request
                        productSolicitud.getCantidad(),
                        productSolicitud.getPrecioBase(),
                        descuento,
                        precioFinal,
                        subtotalProducto,
                        productSolicitud.getCantidad() >= 1 ? "DISPONIBLE" : "NO DISPONIBLE",
                        productSolicitud.getCantidad() >= 1,
                        productSolicitud.getCantidad() >= 1 ? 5 : 15
                );

                productosCotizados.add(productoCotizado);
                subtotal = subtotal.add(subtotalProducto);
            }

            // Calcular totales
            BigDecimal descuentos = calculadoraPrecios.calcularDescuentoTotal(subtotal, request.getTipoCliente());
            BigDecimal impuestos = calculadoraPrecios.calcularImpuestos(subtotal.subtract(descuentos));
            BigDecimal total = subtotal.subtract(descuentos).add(impuestos);

            // Crear respuesta de cotización
            CotizacionResponse cotizacion = new CotizacionResponse(
                    cotizacionId,
                    request.getClienteId(),
                    productosCotizados,
                    subtotal,
                    descuentos,
                    impuestos,
                    total,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusDays(30), // Válida por 30 días
                    "PENDIENTE",
                    request.getObservaciones(),
                    determinarCondicionesPago(request.getTipoCliente()),
                    calcularTiempoEntregaTotal(productosCotizados)
            );

            // Guardar cotización
            return cotizacionRepository.guardarCotizacion(cotizacion);
        }).transformDeferred(CircuitBreakerOperator.of(circuitBreaker))
                .onErrorResume(t -> fallbackCotizacion(request, t));
    }

    public Mono<CotizacionResponse> fallbackCotizacion(CotizacionRequest request, Throwable t) {
        // Manejo de la falla, por ejemplo, retornar una cotización con estado de error
        return Mono.just(new CotizacionResponse(
                UUID.randomUUID().toString(),
                request.getClienteId(),
                Collections.emptyList(),
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                null,
                null,
                "FALLBACK",
                "El servicio no está disponible. Respuesta de fallback.",
                null,
                null
        ));
    }

    @Override
    public Mono<CotizacionResponse>  consultarCotizacion(String cotizacionId) {
        return cotizacionRepository.buscarPorId(cotizacionId);
    }

    @Override
    public Mono<CotizacionResponse> actualizarEstadoCotizacion(String cotizacionId, String nuevoEstado) {
        return cotizacionRepository.buscarPorId(cotizacionId)
                .switchIfEmpty(Mono.error(new RuntimeException("Cotización no encontrada")))
                .flatMap(cotizacion -> {
                    cotizacion.setEstado(nuevoEstado);
                    return cotizacionRepository.actualizarCotizacion(cotizacion);
                });
    }

    private String determinarCondicionesPago(String tipoCliente) {
        return switch (tipoCliente.toUpperCase()) {
            case "RETAIL" -> "Pago contra entrega";
            case "MAYORISTA" -> "30 días";
            case "DISTRIBUIDOR" -> "45 días";
            default -> "Pago anticipado";
        };
    }

    private Integer calcularTiempoEntregaTotal(List<ProductoCotizado> productos) {
        return productos.stream()
                .mapToInt(ProductoCotizado::getTiempoEntrega)
                .max()
                .orElse(5);
    }
}
