package com.soporte.app.domain.port.out;

import com.soporte.app.infrastructure.adapter.out.microservice.dto.CotizacionRequestDto;
import com.soporte.app.infrastructure.adapter.out.microservice.dto.CotizacionResponseDto;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface PriceServicePort {

    CompletableFuture<CotizacionResponseDto> crearCotizacion(CotizacionRequestDto request);

    CompletableFuture<List<CotizacionResponseDto>> obtenerCotizacionesPorCliente(Long customerId);

    CompletableFuture<CotizacionResponseDto> obtenerCotizacion(String cotizacionId);

    CompletableFuture<CotizacionResponseDto> actualizarEstadoCotizacion(String cotizacionId, String nuevoEstado);

    CompletableFuture<Boolean> isServiceAvailable();
}
