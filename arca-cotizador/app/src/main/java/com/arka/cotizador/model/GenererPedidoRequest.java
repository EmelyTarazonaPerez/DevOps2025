package com.arka.cotizador.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenererPedidoRequest {
    private String idProveedor;
    private List<Producto> productos;
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Producto {

        private String codigo;
        private String nombre;
        private Integer cantidad;

    }
}
