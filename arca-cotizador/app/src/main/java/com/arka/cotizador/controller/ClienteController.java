package com.arka.cotizador.controller;

import com.arka.cotizador.model.*;
import com.arka.cotizador.service.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api/cliente/v1")
@RequiredArgsConstructor
public class ClienteController {
    private final PedidoService pedidoService;


    // hacer pedido
    @PostMapping(value = "/hacer-pedido",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<GenererPedidoResponse> generarPedido(@Valid @RequestBody GenererPedidoRequest request) {

        return pedidoService.procesarPedido(request)
                .doOnNext(response -> log.info("Pedido creado exitosamente con ID: {}", response.getPedidoId()));
    }

    // recibir pedido
    @PostMapping(value = "/recibir-pedido/{numeroPedido}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<GenererPedidoResponse> obtenerPedido(@PathVariable String codigoPedido) {
        log.info("Obteniendo pedido con código: {}", codigoPedido);
        return pedidoService.obtenerPedido(codigoPedido)
                .map(request -> GenererPedidoResponse.builder()
                        .pedidoId(codigoPedido)
                        .mensaje(State.valueOf("Pedido encontrado para proveedor: " + request.getIdProveedor()))
                        .build())
                .switchIfEmpty(Mono.just(GenererPedidoResponse.builder()
                        .pedidoId(codigoPedido)
                        .mensaje(State.valueOf("Pedido no encontrado"))
                        .build()))
                .doOnNext(response -> log.info("Respuesta del pedido: {}", response.getMensaje()));
    }

}
