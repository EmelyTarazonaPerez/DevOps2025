package com.cinema.app.repository.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "actores")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="nombre")
    private String name;
    @Column(name="fecha_nacimiento")
    private LocalDateTime birthday;
    @Column(name="create_at")
    private LocalDateTime createAt;
    @Column(name="update_at")
    private LocalDateTime updateAt;
    @ManyToMany(mappedBy = "actors")
    @JsonBackReference
    private List<MovieEntity> movies;
}
