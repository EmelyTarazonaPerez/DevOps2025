package com.cinema.app.servicio.implementation;

import com.cinema.app.repository.implementation.RepositoryMovie;
import com.cinema.app.servicio.IInterfaceDirector;
import com.cinema.app.servicio.model.Movie;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceManager implements IInterfaceDirector {

    private final RepositoryMovie repositoryMovie;

    @Override
    public List<Movie> moviesByManager(Integer id) {
        return repositoryMovie.getAllMovieByManager(id);
    }
}
