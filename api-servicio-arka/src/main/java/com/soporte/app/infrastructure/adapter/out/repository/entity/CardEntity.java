package com.soporte.app.infrastructure.adapter.out.repository.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Table(name = "carrito")
@Entity
@NoArgsConstructor
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "estado")
    private String state;
    @Column(name = "id_cliente")
    private Integer idClient;
    @Column(name = "fecha_actualizacion")
    private Date updatedAt;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<CardProduct> details = new ArrayList<>();

}
