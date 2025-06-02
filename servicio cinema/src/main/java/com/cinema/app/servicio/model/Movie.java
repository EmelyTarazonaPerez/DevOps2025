package com.cinema.app.servicio.model;

import com.cinema.app.repository.entity.Actor;
import com.cinema.app.repository.entity.Gender;
import com.cinema.app.repository.entity.Manager;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
public class Movie {
    public Integer id;
    public String title;
    public String description;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;
    public Manager manager;
    public Gender gender;
    public List<Actor> actors;
}
