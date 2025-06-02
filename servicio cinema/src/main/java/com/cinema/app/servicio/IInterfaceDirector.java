package com.cinema.app.servicio;

import com.cinema.app.servicio.model.Movie;

import java.util.List;

public interface IInterfaceDirector {
    public List<Movie> moviesByManager(Integer id);
}
