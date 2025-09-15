package com.soporte.app.aplication.useCase;


import com.soporte.app.domain.port.out.PriceServicePort;
import com.soporte.app.infrastructure.adapter.in.web.dto.response.ResponseSupportProduct;
import com.soporte.app.infrastructure.adapter.out.microservice.dto.CotizacionResponseDto;
import com.soporte.app.infrastructure.adapter.out.microservice.dto.CotizacionRequestDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class PriceApplicationService {
    
    private static final Logger logger = LoggerFactory.getLogger(PriceApplicationService.class);
    
    private final PriceServicePort priceServicePort;
    
    public PriceApplicationService(PriceServicePort priceServicePort) {
        this.priceServicePort = priceServicePort;
    }

    /**
     * Solicita cotización para productos específicos
     */
    public CompletableFuture<CotizacionResponseDto> solicitarCotizacionProductos(Long customerId, List<ResponseSupportProduct> productos, String tipoCliente) {
        try {
            logger.info("Solicitando cotización para {} productos del cliente: {}", productos.size(), customerId);
            String id = UUID.randomUUID().toString();

            List<CotizacionRequestDto.ProductoCotizacionDto> productosDto = productos.stream()
                .map(producto -> new CotizacionRequestDto.ProductoCotizacionDto(
                        id,
                    producto.getName(),
                    producto.getUnitPrice(),
                    1 // En el modelo actual no tenemos cantidad, usamos 1 por defecto
                ))
                .collect(Collectors.toList());
            
            CotizacionRequestDto request = new CotizacionRequestDto(customerId,  productosDto, tipoCliente);
            
            return priceServicePort.crearCotizacion(request);
            
        } catch (Exception ex) {
            logger.error("Error al procesar solicitud de cotización para productos: {}", ex.getMessage());
            CompletableFuture<CotizacionResponseDto> failedFuture = new CompletableFuture<>();
            failedFuture.completeExceptionally(ex);
            return failedFuture;
        }
    }
    
    /**
     * Obtiene cotizaciones de un cliente
     */
    public CompletableFuture<List<CotizacionResponseDto>> obtenerCotizacionesCliente(String customerId) {
        logger.info("Obteniendo cotizaciones para cliente: {}", customerId);
        return priceServicePort.obtenerCotizacionesPorCliente(customerId);
    }
    
    /**
     * Obtiene una cotización específica
     */
    public CompletableFuture<CotizacionResponseDto> obtenerCotizacion(String cotizacionId) {
        logger.info("Obteniendo cotización: {}", cotizacionId);
        return priceServicePort.obtenerCotizacion(cotizacionId);
    }
    
    /**
     * Actualiza el estado de una cotización
     */
    public CompletableFuture<CotizacionResponseDto> actualizarEstadoCotizacion(String cotizacionId, String nuevoEstado) {
        logger.info("Actualizando estado de cotización {} a: {}", cotizacionId, nuevoEstado);
        return priceServicePort.actualizarEstadoCotizacion(cotizacionId, nuevoEstado);
    }
    
    /**
     * Verifica disponibilidad del servicio
     */
    public CompletableFuture<Boolean> verificarDisponibilidadServicio() {
        return priceServicePort.isServiceAvailable();
    }

}
