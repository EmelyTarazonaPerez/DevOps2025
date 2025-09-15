package com.soporte.app.infrastructure.adapter.out.microservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CotizacionRequestDto {
    private Long customerId;
    private List<ProductoCotizacionDto> productos;
    private String tipoCliente;
    
    public CotizacionRequestDto() {}
    
    public CotizacionRequestDto(Long customerId, List<ProductoCotizacionDto> productos, String tipoCliente) {
        this.customerId = customerId;
        this.productos = productos;
        this.tipoCliente = tipoCliente;
    }

    @Data
    public static class ProductoCotizacionDto {
        private String productId;
        private String nombreProducto;
        private BigDecimal precioBase;
        private Integer cantidad;
        
        public ProductoCotizacionDto() {}

        public ProductoCotizacionDto(String productId, String nombre, BigDecimal precioUnitario, Integer cantidad) {
            this.productId = productId;
            this.nombreProducto = nombre;
            this.precioBase = precioUnitario;
            this.cantidad = cantidad;
        }

     }
}