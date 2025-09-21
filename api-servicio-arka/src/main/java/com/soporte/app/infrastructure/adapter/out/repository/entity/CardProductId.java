package com.soporte.app.infrastructure.adapter.out.repository.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class CardProductId implements Serializable {
    private Integer id_carrito;
    private Integer id_producto;
}
