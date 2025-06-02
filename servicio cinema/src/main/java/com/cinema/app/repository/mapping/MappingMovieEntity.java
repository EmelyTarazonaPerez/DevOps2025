package com.cinema.app.repository.mapping;

import com.cinema.app.repository.entity.MovieEntity;
import com.cinema.app.servicio.model.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MappingMovieEntity {

    MovieEntity modelToEntity(Movie movie);
    Movie entityToModel(MovieEntity entity);
    List<Movie> entityListToModelList(List<MovieEntity> entity);
}
