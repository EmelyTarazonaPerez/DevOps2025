package com.cinema.app.servicio;

import com.cinema.app.servicio.model.Movie;

import java.util.List;

public interface IInterfaceGender {
    public List<Movie> moviesByManager(Integer id);
}
