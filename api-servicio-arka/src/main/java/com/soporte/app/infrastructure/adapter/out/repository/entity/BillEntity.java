package com.soporte.app.infrastructure.adapter.out.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "factura")
public class BillEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "direccion")
    private String direction;
    @Column(name = "id_cliente")
    private Integer idClient;
    @Column(name = "id_carrito")
    private Integer idCard;
    @Column(name = "estado_pedido")
    private String state;
    @Column(name = "precio")
    private Float subtotal;
    @Column(name = "precio_iva")
    private Float iva;
    @Column(name = "fecha_compra")
    private LocalDateTime date;
    @Column(name = "total")
    private Float total;
}
