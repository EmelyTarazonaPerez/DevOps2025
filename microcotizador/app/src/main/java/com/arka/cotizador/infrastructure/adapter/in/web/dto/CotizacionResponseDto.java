package com.arka.cotizador.infrastructure.adapter.in.web.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CotizacionResponseDto {
    private String cotizacionId;
    private String clienteId;
    private List<ProductoCotizadoDto> productos;
    private BigDecimal subtotal;
    private BigDecimal descuentos;
    private BigDecimal impuestos;
    private BigDecimal total;
    private LocalDateTime fechaCotizacion;
    private LocalDateTime fechaVencimiento;
    private String estado;
    private String observaciones;
    private String condicionesPago;
    private Integer tiempoEntrega;

    @Data
    public static class ProductoCotizadoDto {
        private Long productoId;
        private String nombre;
        private String descripcion;
        private Integer cantidad;
        private BigDecimal precioUnitario;
        private BigDecimal descuento;
        private BigDecimal precioFinal;
        private BigDecimal subtotal;
        private String observaciones;
        private Boolean disponible;
        private Integer tiempoEntrega;
    }
}
