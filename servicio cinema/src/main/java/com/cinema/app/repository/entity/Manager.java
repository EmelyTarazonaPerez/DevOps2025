package com.cinema.app.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "directores")
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Integer id;

    @Column(name="nombre")
    private String name;

    @Column(name="create_at")
    private LocalDateTime createAt;

    @Column(name="update_at")
    private LocalDateTime updateAt;

}
