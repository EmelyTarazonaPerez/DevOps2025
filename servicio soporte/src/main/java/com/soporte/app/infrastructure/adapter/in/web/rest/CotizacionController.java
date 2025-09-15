package com.soporte.app.infrastructure.adapter.in.web.rest;


import com.soporte.app.aplication.useCase.PriceApplicationService;
import com.soporte.app.aplication.useCase.SupportProductUseCase;
import com.soporte.app.domain.model.SupportProduct;
import com.soporte.app.infrastructure.adapter.in.web.dto.request.RequestSupportProduct;
import com.soporte.app.infrastructure.adapter.in.web.dto.response.ResponseSupportProduct;
import com.soporte.app.infrastructure.adapter.out.microservice.dto.CotizacionResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/cotizaciones")
public class CotizacionController {
    
    private static final Logger logger = LoggerFactory.getLogger(CotizacionController.class);
    
    private final PriceApplicationService cotizacionService;
    private final SupportProductUseCase productUseCase;
    
    public CotizacionController(PriceApplicationService cotizacionService, SupportProductUseCase productUseCase) {
        this.cotizacionService = cotizacionService;
        this.productUseCase = productUseCase;
    }
    
    @PostMapping("/cliente/{customerId}/productos")
    public CompletableFuture<ResponseEntity<CotizacionResponseDto>> solicitarCotizacionProductos(
            @PathVariable(name = "customerId") Long customerId,
            @RequestBody List<Long> productIds,
            @RequestParam(name = "tipoCliente", defaultValue = "REGULAR") String tipoCliente) {
        
        logger.info("Solicitud de cotización para cliente {} con productos: {}", customerId, productIds);
        
        try {
            // Obtener productos por IDs
            List<ResponseSupportProduct> productos = productIds.stream()
                .map(productUseCase::getProductById)
                .toList();
            
            return cotizacionService.solicitarCotizacionProductos(customerId, productos, tipoCliente)
                .thenApply(ResponseEntity::ok)
                .exceptionally(throwable -> {
                    logger.error("Error al solicitar cotización: {}", throwable.getMessage());
                    return ResponseEntity.internalServerError().build();
                });
                
        } catch (Exception ex) {
            logger.error("Error al procesar solicitud de cotización: {}", ex.getMessage());
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().build());
        }
    }
    
    @GetMapping("/cliente/{customerId}")
    public CompletableFuture<ResponseEntity<List<CotizacionResponseDto>>> obtenerCotizacionesCliente(
            @PathVariable("customerId") String customerId) {
        
        logger.info("Obteniendo cotizaciones para cliente: {}", customerId);
        
        return cotizacionService.obtenerCotizacionesCliente(customerId)
            .thenApply(ResponseEntity::ok)
            .exceptionally(throwable -> {
                logger.error("Error al obtener cotizaciones del cliente {}: {}", customerId, throwable.getMessage());
                return ResponseEntity.internalServerError().build();
            });
    }
    
    @GetMapping("/{cotizacionId}")
    public CompletableFuture<ResponseEntity<CotizacionResponseDto>> obtenerCotizacion(
            @PathVariable("cotizacionId") String cotizacionId) {
        
        logger.info("Obteniendo cotización: {}", cotizacionId);
        
        return cotizacionService.obtenerCotizacion(cotizacionId)
            .thenApply(ResponseEntity::ok)
            .exceptionally(throwable -> {
                logger.error("Error al obtener cotización {}: {}", cotizacionId, throwable.getMessage());
                return ResponseEntity.notFound().build();
            });
    }
    
    @PutMapping("/{cotizacionId}/estado")
    public CompletableFuture<ResponseEntity<CotizacionResponseDto>> actualizarEstadoCotizacion(
            @PathVariable("cotizacionId") String cotizacionId,
            @RequestBody String nuevoEstado) {
        
        logger.info("Actualizando estado de cotización {} a: {}", cotizacionId, nuevoEstado);
        
        return cotizacionService.actualizarEstadoCotizacion(cotizacionId, nuevoEstado)
            .thenApply(ResponseEntity::ok)
            .exceptionally(throwable -> {
                logger.error("Error al actualizar estado de cotización {}: {}", cotizacionId, throwable.getMessage());
                return ResponseEntity.internalServerError().build();
            });
    }
    
    @GetMapping("/servicio/health")
    public CompletableFuture<ResponseEntity<String>> verificarServicioCotizacion() {
        return cotizacionService.verificarDisponibilidadServicio()
            .thenApply(disponible -> {
                if (disponible) {
                    return ResponseEntity.ok("Servicio de cotización disponible");
                } else {
                    return ResponseEntity.status(503).body("Servicio de cotización no disponible");
                }
            });
    }
}
