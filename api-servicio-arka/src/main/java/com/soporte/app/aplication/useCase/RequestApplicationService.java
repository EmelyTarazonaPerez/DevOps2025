package com.soporte.app.aplication.useCase;


import com.soporte.app.domain.port.out.RequestManagerServicePort;
import com.soporte.app.infrastructure.adapter.in.web.dto.response.ResponseSupportProduct;
import com.soporte.app.infrastructure.adapter.out.microservice.dto.SolicitudRequestDto;
import com.soporte.app.infrastructure.adapter.out.microservice.dto.SolicitudResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class RequestApplicationService {
    
    private static final Logger logger = LoggerFactory.getLogger(RequestApplicationService.class);
    
    private final RequestManagerServicePort requestManagerServicePort;
    
    public RequestApplicationService(RequestManagerServicePort requestManagerServicePort) {
        this.requestManagerServicePort = requestManagerServicePort;
    }
    
    /**
     * Crea una solicitud de productos
     */
    public CompletableFuture<SolicitudResponseDto> crearSolicitudProductos(String customerId, List<ResponseSupportProduct> productos,
                                                                           String tipoSolicitud, String prioridad, String observaciones) {
        try {
            logger.info("Creando solicitud para cliente: {} con {} productos", customerId, productos.size());
            String id = UUID.randomUUID().toString();

            List<SolicitudRequestDto.ProductoSolicitudDto> productosDto = productos.stream()
                .map(producto -> new SolicitudRequestDto.ProductoSolicitudDto(
                    id,
                    producto.getName(),
                    1,// Cantidad por defecto
                        null
                ))
                .collect(Collectors.toList());
            
            SolicitudRequestDto request = new SolicitudRequestDto(customerId, tipoSolicitud, prioridad, productosDto, observaciones);
            
            return (CompletableFuture<SolicitudResponseDto>) requestManagerServicePort.crearSolicitud(request)
                .whenComplete((result, throwable) -> {
                    if (throwable != null) {
                        logger.error("Error al crear solicitud: {}", throwable.getMessage());
                    } else {
                        logger.info("Solicitud creada exitosamente: {}", result.getSolicitudId());
                    }
                });
                
        } catch (Exception ex) {
            logger.error("Error al procesar creación de solicitud: {}", ex.getMessage());
            CompletableFuture<SolicitudResponseDto> failedFuture = new CompletableFuture<>();
            failedFuture.completeExceptionally(ex);
            return failedFuture;
        }
    }
    
    /**
     * Crea una solicitud de cotización
     */
    public CompletableFuture<SolicitudResponseDto> crearSolicitudCotizacion(String customerId, List<ResponseSupportProduct> productos, String observaciones) {
        return crearSolicitudProductos(customerId, productos, "COTIZACION", "MEDIA", observaciones);
    }
    
    /**
     * Crea una solicitud de información
     */
    public CompletableFuture<SolicitudResponseDto> crearSolicitudInformacion(String customerId, List<ResponseSupportProduct> productos, String observaciones) {
        return crearSolicitudProductos(customerId, productos, "INFORMACION", "BAJA", observaciones);
    }
    
    /**
     * Crea una solicitud urgente
     */
    public CompletableFuture<SolicitudResponseDto> crearSolicitudUrgente(String customerId, List<ResponseSupportProduct> productos, String observaciones) {
        return crearSolicitudProductos(customerId, productos, "URGENTE", "ALTA", observaciones);
    }
    
    /**
     * Obtiene solicitudes de un cliente
     */
    public CompletableFuture<List<SolicitudResponseDto>> obtenerSolicitudesCliente(Long customerId) {
        logger.info("Obteniendo solicitudes para cliente: {}", customerId);
        return requestManagerServicePort.obtenerSolicitudesPorCliente(customerId);
    }
    
    /**
     * Obtiene una solicitud específica
     */
    public CompletableFuture<SolicitudResponseDto> obtenerSolicitud(String solicitudId) {
        logger.info("Obteniendo solicitud: {}", solicitudId);
        return requestManagerServicePort.obtenerSolicitud(solicitudId);
    }
    
    /**
     * Obtiene solicitudes por estado
     */
    public CompletableFuture<List<SolicitudResponseDto>> obtenerSolicitudesPorEstado(String estado) {
        logger.info("Obteniendo solicitudes por estado: {}", estado);
        return requestManagerServicePort.obtenerSolicitudesPorEstado(estado);
    }
    
    /**
     * Actualiza el estado de una solicitud
     */
    public CompletableFuture<SolicitudResponseDto> actualizarEstadoSolicitud(String solicitudId, String nuevoEstado) {
        logger.info("Actualizando estado de solicitud {} a: {}", solicitudId, nuevoEstado);
        return requestManagerServicePort.actualizarEstadoSolicitud(solicitudId, nuevoEstado);
    }
    
    /**
     * Verifica disponibilidad del servicio
     */
    public CompletableFuture<Boolean> verificarDisponibilidadServicio() {
        return requestManagerServicePort.isServiceAvailable();
    }
}
