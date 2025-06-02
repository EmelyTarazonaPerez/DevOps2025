package com.cinema.app.repository.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Setter
@Getter
@Table(name = "peliculas")
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "titulo")
    private String title;
    @Column(name = "descripcion")
    private String description;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Manager manager;

    @ManyToOne
    @JoinColumn(name = "genero_id")
    private Gender gender;

    @ManyToMany
    @JoinTable(
            name = "peliculas_has_actores",
            joinColumns = @JoinColumn(name = "pelicula_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    @JsonManagedReference
    private List<Actor> actors;
}
