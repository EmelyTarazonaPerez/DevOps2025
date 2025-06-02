package com.cliente.app.infrastructure.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_cliente")
    private Integer id;

    @Column(name="nombre")
    private String name;
    @Column(name="apellido")
    private String lastName;
    @Column(name="edad")
    private String age;
    @Column(name="telefono")
    private String phone;
    @Column(name="num_identificacion")
    private String identificationNumber;
    @Column(name="tipo_identificacion")
    private String identificationType;



}
