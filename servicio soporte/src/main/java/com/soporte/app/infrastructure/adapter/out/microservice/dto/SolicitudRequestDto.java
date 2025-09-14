package com.soporte.app.infrastructure.adapter.out.microservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class SolicitudRequestDto {
    private Long proveedorId;
    private String tipoSolicitud;
    private String prioridad;
    private List<ProductoSolicitudDto> productos;
    private String observaciones;

    public SolicitudRequestDto() {
    }

    public SolicitudRequestDto(Long customerId, String tipoSolicitud, String prioridad,
                               List<ProductoSolicitudDto> productos, String observaciones) {
        this.proveedorId = customerId;
        this.tipoSolicitud = tipoSolicitud;
        this.prioridad = prioridad;
        this.productos = productos;
        this.observaciones = observaciones;
    }

    @Data
    public static class ProductoSolicitudDto {
        private String productId;
        private String nombreProducto;
        private Integer cantidadSolicitada;
        private String especificaciones;

        public ProductoSolicitudDto() {
        }

        public ProductoSolicitudDto(String productId, String nombre, Integer cantidadSolicitada, String especificaciones) {
            this.productId = productId;
            this.nombreProducto = nombre;
            this.cantidadSolicitada = cantidadSolicitada;
            this.especificaciones = especificaciones;
        }

    }
}
