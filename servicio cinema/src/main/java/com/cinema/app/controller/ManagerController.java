package com.cinema.app.controller;

import com.cinema.app.servicio.IInterfaceDirector;
import com.cinema.app.servicio.model.Movie;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.RequestPath;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/director")
public class ManagerController {

    private final IInterfaceDirector interfaceDirector;

    @GetMapping("/{id}/peliculas")
    public ResponseEntity<List<Movie>>  getAllMoviesByManager(@PathVariable Integer id) {
        return new ResponseEntity<>(interfaceDirector.moviesByManager(id), HttpStatus.OK) ;
    }
}
