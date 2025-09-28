package com.arka.cotizador.infrastructure.adapter.out.persistence;

import com.arka.cotizador.domain.model.CotizacionResponse;
import com.arka.cotizador.domain.port.out.CotizacionRepositoryPort;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CotizacionRepositoryAdapter implements CotizacionRepositoryPort {

    // Simulando una base de datos en memoria para las cotizaciones
    private final Map<String, CotizacionResponse> cotizaciones = new ConcurrentHashMap<>();

    @Override
    public CotizacionResponse guardarCotizacion(CotizacionResponse cotizacion) {
        cotizaciones.put(cotizacion.getCotizacionId(), cotizacion);
        return cotizacion;
    }

    @Override
    public Mono<CotizacionResponse> buscarPorId(String cotizacionId) {
        return Mono.justOrEmpty(cotizaciones.get(cotizacionId));
    }


    @Override
    public Mono<CotizacionResponse> actualizarCotizacion(CotizacionResponse cotizacion) {
        if (cotizaciones.containsKey(cotizacion.getCotizacionId())) {
            cotizaciones.put(cotizacion.getCotizacionId(), cotizacion);
            return Mono.just(cotizacion); // ✔️ Devuelve un Mono
        }
        return Mono.empty(); // ✔️ Mejor que null, indica "no encontrado"
    }
}
