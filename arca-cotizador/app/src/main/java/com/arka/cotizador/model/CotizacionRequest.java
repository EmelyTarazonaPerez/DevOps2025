package com.arka.cotizador.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CotizacionRequest {
    
    @NotNull(message = "El nombre del cliente es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombreCliente;
    
    @NotNull(message = "El email del cliente es obligatorio")
    private String emailCliente;
    
    @NotNull(message = "La lista de productos es obligatoria")
    @Size(min = 1, message = "Debe incluir al menos un producto")
    private List<ProductoCotizacion> productos;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaSolicitud;
    
    private String observaciones;
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductoCotizacion {
        
        @NotNull(message = "El código del producto es obligatorio")
        private String codigo;
        
        @NotNull(message = "El nombre del producto es obligatorio")
        private String nombre;
        
        @NotNull(message = "La cantidad es obligatoria")
        @Positive(message = "La cantidad debe ser mayor a cero")
        private Integer cantidad;
        
        private String categoria;
        
        private String descripcion;
    }
}