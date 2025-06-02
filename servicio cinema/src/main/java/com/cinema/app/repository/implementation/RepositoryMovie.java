package com.cinema.app.repository.implementation;


import com.cinema.app.repository.IRepositoryMovie;
import com.cinema.app.repository.entity.MovieEntity;
import com.cinema.app.repository.mapping.MappingMovieEntity;
import com.cinema.app.servicio.model.Movie;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RepositoryMovie  {
    private final IRepositoryMovie repositoryMovie;
    private final MappingMovieEntity mappingMovie;

    public Optional<Movie> get(Integer id) {
        return repositoryMovie.findById(id)
                .map(mappingMovie::entityToModel);
    }

    public List<Movie> getAll() {
        return mappingMovie.entityListToModelList(repositoryMovie.findAll());
    }

    public Movie save(Movie movie) {
        MovieEntity movieEntity = mappingMovie.modelToEntity(movie);
        return mappingMovie.entityToModel(repositoryMovie.save(movieEntity));
    }

    public Movie update(Movie movie) {
        return mappingMovie.entityToModel(repositoryMovie.save(mappingMovie.modelToEntity(movie)));
    }

    public void delete(Integer id) {
        repositoryMovie.deleteById(id);
    }

    public List<Movie> getAllMovieByManager(Integer id) {
        return mappingMovie.entityListToModelList(repositoryMovie.findAllByManagerId(id));
    }

    public List<Movie> getAllMovieByGender(Integer id) {
        return mappingMovie.entityListToModelList(repositoryMovie.findAllByGenderId(id));
    }
}
