package com.arka.cotizador.controller;

import com.arka.cotizador.model.CotizacionRequest;
import com.arka.cotizador.model.CotizacionResponse;
import com.arka.cotizador.model.GenererPedidoRequest;
import com.arka.cotizador.service.CotizacionService;
import com.arka.cotizador.service.PedidoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import jakarta.validation.Valid;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api/provedor/v1")
@RequiredArgsConstructor
public class CotizacionController {
    
    private final CotizacionService cotizacionService;
    private final PedidoService pedidoService;

    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CotizacionResponse> crearCotizacion(@Valid @RequestBody CotizacionRequest request) {
        log.info("Recibida solicitud de cotización para cliente: {}", request.getNombreCliente());
        
        if (request.getFechaSolicitud() == null) {
            request.setFechaSolicitud(LocalDateTime.now());
        }
        
        return cotizacionService.procesarCotizacion(request)
                .doOnNext(response -> log.info("Cotización creada exitosamente con ID: {}", response.getId()));
    }

    @PostMapping(
            value = "/generarPedidoMayorista ",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CotizacionResponse> crearPedidoMayorista(@Valid @RequestBody GenererPedidoRequest request) {
        log.info("Recibida solicitud de pedido mayorista para proveedor: {}", request.getIdProveedor());

        return pedidoService.procesarPedido(request)
                .flatMap(pedidoResponse -> {
                    CotizacionResponse response = new CotizacionResponse();
                    response.setId(pedidoResponse.getPedidoId());
                    response.setNombreCliente("Proveedor ID: " + request.getIdProveedor());
                    response.setEmailCliente("N/A");
                    response.setFechaCotizacion(LocalDateTime.now());
                    response.setFechaVencimiento(LocalDateTime.now().plusDays(30));
                    response.setTotal(null);
                    response.setImpuestos(null);
                    response.setSubtotal(null);
                    return Mono.just(response);
                })
                .doOnNext(response -> log.info("Pedido mayorista creado exitosamente con ID: {}", response.getId()));
    }

    @GetMapping("/health")
    public Mono<String> health() {
        return Mono.just("Cotizaciones Controller - OK");
    }
    
    @GetMapping("/info")
    public Mono<Object> info() {
        return Mono.just(new Object() {
            public final String service = "Arca Cotizador";
            public final String version = "1.0.0";
            public final LocalDateTime timestamp = LocalDateTime.now();
            public final String description = "Microservicio para gestión de cotizaciones";
        });
    }
}