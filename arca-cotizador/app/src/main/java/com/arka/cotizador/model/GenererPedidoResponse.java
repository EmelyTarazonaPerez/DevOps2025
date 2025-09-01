package com.arka.cotizador.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenererPedidoResponse {
    String pedidoId;
    State mensaje;
}
