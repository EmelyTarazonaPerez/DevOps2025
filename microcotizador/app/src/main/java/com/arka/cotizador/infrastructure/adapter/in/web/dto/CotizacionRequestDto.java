package com.arka.cotizador.infrastructure.adapter.in.web.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CotizacionRequestDto {
    private String clienteId;
    private List<ProductoSolicitadoDto> productos;
    private String tipoCliente;
    private String observaciones;

    @Data
    public static class ProductoSolicitadoDto {
        private Long productoId;
        private String nombreProducto;
        private Integer cantidad;
        private BigDecimal precioBase;
        private String observaciones;
    }
}
