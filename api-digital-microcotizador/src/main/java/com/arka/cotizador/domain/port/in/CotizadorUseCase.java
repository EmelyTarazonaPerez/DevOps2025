package com.arka.cotizador.domain.port.in;

import com.arka.cotizador.domain.model.CotizacionRequest;
import com.arka.cotizador.domain.model.CotizacionResponse;
import reactor.core.publisher.Mono;

public interface CotizadorUseCase {
    
    /**
     * Genera una cotización basada en el request recibido
     * @param request Datos de la solicitud de cotización
     * @return Cotización generada
     */
    Mono<CotizacionResponse> generarCotizacion(CotizacionRequest request);
    
    /**
     * Consulta una cotización por su ID
     * @param cotizacionId ID de la cotización
     * @return Cotización encontrada
     */
    Mono<CotizacionResponse>  consultarCotizacion(String cotizacionId);
    
    /**
     * Actualiza el estado de una cotización
     * @param cotizacionId ID de la cotización
     * @param nuevoEstado Nuevo estado
     * @return Cotización actualizada
     */
    Mono<CotizacionResponse> actualizarEstadoCotizacion(String cotizacionId, String nuevoEstado);
}
