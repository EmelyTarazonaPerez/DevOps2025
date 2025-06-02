package com.cinema.app.controller;

import com.cinema.app.servicio.IInterfaceGender;
import com.cinema.app.servicio.model.Movie;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/generos")
public class GenderController {
    private final IInterfaceGender interfaceGender;

    @GetMapping("/{id}/peliculas")
    public ResponseEntity<List<Movie>> getAllMoviesByManager(@PathVariable Integer id) {
        return new ResponseEntity<>(interfaceGender.moviesByManager(id), HttpStatus.OK);
    }
}
