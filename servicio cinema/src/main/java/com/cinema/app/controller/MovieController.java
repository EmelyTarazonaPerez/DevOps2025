package com.cinema.app.controller;

import com.cinema.app.controller.dto.RequestMovie;
import com.cinema.app.servicio.InterfaceMovie;
import com.cinema.app.servicio.model.Movie;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/peliculas")
public class MovieController {

    private final InterfaceMovie interfaceMovie;

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = interfaceMovie.getAllMovies();
        return ResponseEntity.ok(movies); // HttpStatus 200
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable Integer id) {
        return interfaceMovie.getMovie(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Movie> newMovie(@RequestBody RequestMovie movie) {
        Movie savedMovie = interfaceMovie.saveMovie(movie);
        return new ResponseEntity<>(savedMovie, HttpStatus.CREATED); // 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Integer id, @RequestBody RequestMovie movie) {
        try {
            Movie updated = interfaceMovie.updateMovie(movie, id);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Integer id) {
        try {
            String msg = interfaceMovie.deleteMovie(id);
            return ResponseEntity.ok(msg);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Película no encontrada con ID: " + id);
        }
    }

}
