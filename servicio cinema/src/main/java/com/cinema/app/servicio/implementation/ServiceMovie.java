package com.cinema.app.servicio.implementation;

import com.cinema.app.controller.dto.RequestMovie;
import com.cinema.app.repository.IRepositoryActor;
import com.cinema.app.repository.entity.Actor;
import com.cinema.app.repository.entity.Gender;
import com.cinema.app.repository.entity.Manager;
import com.cinema.app.repository.implementation.RepositoryActor;
import com.cinema.app.repository.implementation.RepositoryGender;
import com.cinema.app.repository.implementation.RepositoryManager;
import com.cinema.app.repository.implementation.RepositoryMovie;
import com.cinema.app.servicio.InterfaceMovie;
import com.cinema.app.servicio.model.Movie;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ServiceMovie implements InterfaceMovie {

    private final RepositoryMovie repositoryMovie;
    private final RepositoryActor repositoryActor;
    private final RepositoryGender repositoryGender;
    private final RepositoryManager repositoryManager;


    @Override
    public Optional<Movie> getMovie(Integer id) {
        return repositoryMovie.get(id);
    }

    @Override
    public Movie updateMovie(RequestMovie movie, Integer id) {
        Movie existingMovie = repositoryMovie.get(id).orElseThrow(
                () -> new RuntimeException("Pelicula no encontrada con ID: " + id)
        );

        Movie updatedMovie = mapper(movie, existingMovie);
        updatedMovie.setUpdatedAt(LocalDateTime.now());
        return repositoryMovie.save(updatedMovie);
    }

    @Override
    public Movie saveMovie(RequestMovie movie) {
        Movie movieModel = mapper(movie, new Movie());
        movieModel.setCreatedAt(LocalDateTime.now());
        return repositoryMovie.save(movieModel);
    }

    private Movie mapper (RequestMovie movie, Movie entity) {
        entity.setTitle(movie.getTitle());
        entity.setDescription(movie.getDescription());

        Manager manager = repositoryManager.findById(movie.getIdManager())
                .orElseThrow(() -> new RuntimeException("Autor no encontrado con ID: " + movie.getIdManager()));
        entity.setManager(manager);

        Gender gender = repositoryGender.findById(movie.getIdGender())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + movie.getIdGender()));
        entity.setGender(gender);

        List<Actor> actors = movie.getIdActors().stream()
                .map(id -> repositoryActor.findById(id)
                        .orElseThrow(() -> new RuntimeException("Actor no encontrado con ID: " + id)))
                .collect(Collectors.toList());
        entity.setActors(actors);

        return entity;
    }

    @Override
    public List<Movie> getAllMovies() {
        return repositoryMovie.getAll();
    }

    @Override
    public String deleteMovie(Integer id) {
        repositoryMovie.delete(id);
        return "Eliminado exitosamente";
    }
}
