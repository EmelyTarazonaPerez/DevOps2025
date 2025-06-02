package com.cinema.app.servicio.implementation;

import com.cinema.app.repository.implementation.RepositoryMovie;
import com.cinema.app.servicio.IInterfaceGender;
import com.cinema.app.servicio.model.Movie;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceGender implements IInterfaceGender {

    private final RepositoryMovie repositoryGenere;
    @Override
    public List<Movie> moviesByManager(Integer id) {
        return repositoryGenere.getAllMovieByGender(id);
    }
}
