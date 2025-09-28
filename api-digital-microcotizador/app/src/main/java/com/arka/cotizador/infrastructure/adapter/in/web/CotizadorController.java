package com.arka.cotizador.infrastructure.adapter.in.web;


import com.arka.cotizador.domain.model.CotizacionRequest;
import com.arka.cotizador.domain.model.CotizacionResponse;
import com.arka.cotizador.domain.port.in.CotizadorUseCase;
import com.arka.cotizador.infrastructure.adapter.in.web.dto.CotizacionRequestDto;
import com.arka.cotizador.infrastructure.adapter.in.web.dto.CotizacionResponseDto;
import com.arka.cotizador.infrastructure.adapter.in.web.mapper.CotizadorWebMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/cotizador")
@CrossOrigin(origins = "*")
public class CotizadorController {

    private final CotizadorUseCase cotizadorUseCase;
    private final CotizadorWebMapper mapper;

    public CotizadorController(CotizadorUseCase cotizadorUseCase, CotizadorWebMapper mapper) {
        this.cotizadorUseCase = cotizadorUseCase;
        this.mapper = mapper;
    }

    @PostMapping("/cotizaciones")
    public ResponseEntity<Mono<CotizacionResponseDto>> generarCotizacion(@RequestBody CotizacionRequestDto requestDto) {
            CotizacionRequest request = mapper.toDomain(requestDto);
            Mono<CotizacionResponse> response = cotizadorUseCase.generarCotizacion(request);
            Mono<CotizacionResponseDto> responseDto = mapper.toDto(response);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/cotizaciones/{cotizacionId}")
    public ResponseEntity<Mono<CotizacionResponseDto>> consultarCotizacion(@PathVariable String cotizacionId) {
        try {
            Mono<CotizacionResponse> response = cotizadorUseCase.consultarCotizacion(cotizacionId);
            if (response != null) {
                Mono<CotizacionResponseDto> responseDto = mapper.toDto(response);
                return ResponseEntity.ok(responseDto);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/cotizaciones/{cotizacionId}/estado")
    public ResponseEntity<Mono<CotizacionResponseDto>> actualizarEstado(
            @PathVariable String cotizacionId,
            @RequestParam String nuevoEstado) {
        try {
            Mono<CotizacionResponse> response = cotizadorUseCase.actualizarEstadoCotizacion(cotizacionId, nuevoEstado);
            if (response != null) {
                Mono<CotizacionResponseDto> responseDto = mapper.toDto(response);
                return ResponseEntity.ok(responseDto);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Cotizador service is running");
    }
}
