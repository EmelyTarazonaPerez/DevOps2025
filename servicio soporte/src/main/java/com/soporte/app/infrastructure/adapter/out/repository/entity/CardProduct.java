package com.soporte.app.infrastructure.adapter.out.repository.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@Entity
@Table(name = "carritoproducto")
@ToString(exclude = "card")
public class CardProduct {

    @EmbeddedId
    private CardProductId id = new CardProductId();
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("id_carrito")
    @JoinColumn(name = "id_carrito")
    @JsonBackReference
    private CardEntity card;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("id_producto")
    @JoinColumn(name = "id_producto")
    private ProductEntity product;
    private Integer cantidad;
}
