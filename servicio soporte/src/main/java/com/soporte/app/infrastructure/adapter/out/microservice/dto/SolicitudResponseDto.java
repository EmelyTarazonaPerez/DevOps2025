package com.soporte.app.infrastructure.adapter.out.microservice.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SolicitudResponseDto {
    private String solicitudId;
    private String proveedorId;
    private String tipoSolicitud;
    private List<SolicitudRequestDto.ProductoSolicitudDto> productos;
    private String estado;
    private LocalDateTime fechaSolicitud;
    private LocalDateTime fechaRespuesta;
    private String observaciones;
    private String prioridad;

    public SolicitudResponseDto() {}

}
