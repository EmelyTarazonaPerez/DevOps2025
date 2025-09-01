package com.arka.cotizador.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CotizacionResponse {
    
    private String id;
    
    private String nombreCliente;
    
    private String emailCliente;
    
    private List<ProductoCotizado> productos;
    
    private BigDecimal subtotal;
    
    private BigDecimal impuestos;
    
    private BigDecimal total;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaCotizacion;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaVencimiento;
    
    private String moneda;
    
    private String estado;
    
    private String observaciones;
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductoCotizado {
        
        private String codigo;
        
        private String nombre;
        
        private Integer cantidad;
        
        private BigDecimal precioUnitario;
        
        private BigDecimal descuento;
        
        private BigDecimal subtotal;
        
        private String categoria;
        
        private String descripcion;
    }
}