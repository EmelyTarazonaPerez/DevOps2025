package com.arka.gestorsolicitudes.infrastructure.adapter.in.web.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SolicitudProveedorRequestDto {
    private String proveedorId;
    private String tipoSolicitud;
    private List<ProductoSolicitadoDto> productos;
    private String observaciones;
    private String prioridad;

    public SolicitudProveedorRequestDto() {}

    public static class ProductoSolicitadoDto {
        private Long productoId;
        private String nombreProducto;
        private Integer cantidadSolicitada;
        private BigDecimal precioReferencia;
        private String especificaciones;
        private String observaciones;
        private Boolean urgente;

        public ProductoSolicitadoDto() {}

        // Getters y Setters
        public Long getProductoId() {
            return productoId;
        }

        public void setProductoId(Long productoId) {
            this.productoId = productoId;
        }

        public String getNombreProducto() {
            return nombreProducto;
        }

        public void setNombreProducto(String nombreProducto) {
            this.nombreProducto = nombreProducto;
        }

        public Integer getCantidadSolicitada() {
            return cantidadSolicitada;
        }

        public void setCantidadSolicitada(Integer cantidadSolicitada) {
            this.cantidadSolicitada = cantidadSolicitada;
        }

        public BigDecimal getPrecioReferencia() {
            return precioReferencia;
        }

        public void setPrecioReferencia(BigDecimal precioReferencia) {
            this.precioReferencia = precioReferencia;
        }

        public String getEspecificaciones() {
            return especificaciones;
        }

        public void setEspecificaciones(String especificaciones) {
            this.especificaciones = especificaciones;
        }

        public String getObservaciones() {
            return observaciones;
        }

        public void setObservaciones(String observaciones) {
            this.observaciones = observaciones;
        }

        public Boolean getUrgente() {
            return urgente;
        }

        public void setUrgente(Boolean urgente) {
            this.urgente = urgente;
        }
    }
}
