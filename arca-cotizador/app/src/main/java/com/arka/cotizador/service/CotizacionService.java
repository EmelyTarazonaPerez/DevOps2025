package com.arka.cotizador.service;

import com.arka.cotizador.model.CotizacionRequest;
import com.arka.cotizador.model.CotizacionResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CotizacionService {
    
    private static final BigDecimal TASA_IMPUESTO = new BigDecimal("0.19");
    private static final int DIAS_VALIDEZ = 30;
    private final List<String> pedidos = new ArrayList<>();

    public Mono<CotizacionResponse> procesarCotizacion(CotizacionRequest request) {
        log.info("Procesando cotización para cliente: {}", request.getNombreCliente());
        
        return Mono.fromCallable(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return calcularCotizacion(request);
        })
        .doOnSuccess(response -> log.info("Cotización procesada con ID: {}", response.getId()))
        .doOnError(error -> log.error("Error procesando cotización", error));
    }
    
    private CotizacionResponse calcularCotizacion(CotizacionRequest request) {
        String id = UUID.randomUUID().toString();
        LocalDateTime fechaCotizacion = LocalDateTime.now();
        LocalDateTime fechaVencimiento = fechaCotizacion.plusDays(DIAS_VALIDEZ);
        
        List<CotizacionResponse.ProductoCotizado> productos = request.getProductos().stream()
                .map(this::calcularProducto)
                .collect(Collectors.toList());
        
        BigDecimal subtotal = productos.stream()
                .map(CotizacionResponse.ProductoCotizado::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        BigDecimal impuestos = subtotal.multiply(TASA_IMPUESTO);
        BigDecimal total = subtotal.add(impuestos);
        
        return CotizacionResponse.builder()
                .id(id)
                .nombreCliente(request.getNombreCliente())
                .emailCliente(request.getEmailCliente())
                .productos(productos)
                .subtotal(subtotal)
                .impuestos(impuestos)
                .total(total)
                .fechaCotizacion(fechaCotizacion)
                .fechaVencimiento(fechaVencimiento)
                .moneda("COP")
                .estado("ACTIVA")
                .observaciones(request.getObservaciones())
                .build();
    }
    
    private CotizacionResponse.ProductoCotizado calcularProducto(CotizacionRequest.ProductoCotizacion producto) {
        BigDecimal precioUnitario = calcularPrecioUnitario(producto.getCodigo());
        BigDecimal descuento = BigDecimal.ZERO;
        BigDecimal subtotal = precioUnitario.multiply(new BigDecimal(producto.getCantidad()));
        
        return CotizacionResponse.ProductoCotizado.builder()
                .codigo(producto.getCodigo())
                .nombre(producto.getNombre())
                .cantidad(producto.getCantidad())
                .precioUnitario(precioUnitario)
                .descuento(descuento)
                .subtotal(subtotal)
                .categoria(producto.getCategoria())
                .descripcion(producto.getDescripcion())
                .build();
    }
    
    private BigDecimal calcularPrecioUnitario(String codigo) {
        int hash = Math.abs(codigo.hashCode());
        int precio = (hash % 100000) + 10000;
        return new BigDecimal(precio);
    }

    public Mono<String > cotizacionMayorista(@Valid CotizacionRequest request) {
        log.info("Procesando pedido mayorista para cliente: {}", request.getNombreCliente());
        // Aquí se podría agregar lógica específica para pedidos mayoristas

        boolean existeProducto = request.getProductos().stream()
                .anyMatch(producto -> existeProductoEnPedidos(producto.getCodigo()));
        if (!existeProducto) return Mono.error(new IllegalArgumentException("Ningún producto del pedido existe en los registros."));

        String codigoPedido = UUID.randomUUID().toString();;
        pedidos.add(codigoPedido);
        log.info("Pedido mayorista procesado con código: {}", codigoPedido);

        return "Pedido procesado con código: ".equals(codigoPedido) ?
                Mono.error(new IllegalStateException("Error al generar el código del pedido.")) :
                Mono.just(codigoPedido);
    }


    public boolean existeProductoEnPedidos(String codigoProducto) {
        return pedidos.stream().anyMatch(pedido -> pedido.contains(codigoProducto));
    }
}