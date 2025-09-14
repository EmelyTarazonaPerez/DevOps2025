package com.soporte.app.domain.port.out;

import com.soporte.app.infrastructure.adapter.out.microservice.dto.SolicitudRequestDto;
import com.soporte.app.infrastructure.adapter.out.microservice.dto.SolicitudResponseDto;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public interface RequestManagerServicePort {
    CompletionStage<SolicitudResponseDto> crearSolicitud(SolicitudRequestDto request);
    CompletableFuture<List<SolicitudResponseDto>> obtenerSolicitudesPorCliente(Long customerId);
    CompletableFuture<SolicitudResponseDto> obtenerSolicitud(String solicitudId);
    CompletableFuture<List<SolicitudResponseDto>> obtenerSolicitudesPorEstado(String estado);
    CompletableFuture<SolicitudResponseDto> actualizarEstadoSolicitud(String solicitudId, String nuevoEstado);
    CompletableFuture<Boolean> isServiceAvailable();
}
