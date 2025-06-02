package com.cinema.app.servicio;

import com.cinema.app.controller.dto.RequestMovie;
import com.cinema.app.servicio.model.Movie;

import java.util.List;
import java.util.Optional;

public interface InterfaceMovie {
    public Optional<Movie> getMovie(Integer id);
    public Movie updateMovie(RequestMovie movie, Integer id);
    public Movie saveMovie(RequestMovie movie);
    public List<Movie> getAllMovies();
    public String deleteMovie(Integer id);
}
