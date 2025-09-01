package com.arka.cotizador.service;

import com.arka.cotizador.model.*;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class PedidoService {

    private final Map<String, GenererPedidoRequest> pedidos = new ConcurrentHashMap<>();

    public Mono<GenererPedidoResponse> procesarPedido(@Valid GenererPedidoRequest request) {
        log.info("Procesando pedido para proveedor: {}", request.getIdProveedor());

        return Mono.fromCallable(() -> {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    return generarCodigoPedido(request);
                })
                .doOnSuccess(codigo -> log.info("Pedido procesado con código: {}", codigo))
                .doOnError(error -> log.error("Error procesando pedido", error));
    }

    private GenererPedidoResponse generarCodigoPedido(@Valid GenererPedidoRequest request) {
        String codigo = "PED-" + new Random().nextInt(100000);
        pedidos.put(codigo, request);
        return GenererPedidoResponse.builder()
                .pedidoId(codigo)
                .mensaje(State.PENDING)
                .build();
    }

    public Mono<GenererPedidoRequest> obtenerPedido(String codigoPedido) {
        return Mono.justOrEmpty(pedidos.get(codigoPedido));
    }

}


