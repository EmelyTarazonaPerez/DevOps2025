package com.microservicio.comentarios.controller;

import com.microservicio.comentarios.model.Comentario;
import com.microservicio.comentarios.model.ComentarioDto;
import com.microservicio.comentarios.service.ComentarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentarios")
@RequiredArgsConstructor
public class ComentarioController {

    private final ComentarioService service;

    @PostMapping
    public ResponseEntity<Comentario> crear(@RequestBody ComentarioDto comentario) {
        return ResponseEntity.ok(service.crear(comentario));
    }

    /**
     * Endpoint para destacar un comentario incrementando su contador de "likes"
     */
    @PutMapping("/{id}/destacar")
    public ResponseEntity<Comentario> destacar(@PathVariable String id) {
        Comentario comentario = service.listar( 0, Integer.MAX_VALUE).stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado"));
        int nuevosLikes = comentario.getLike() + 1;
        comentario.setLike(nuevosLikes);
        return ResponseEntity.ok(service.actualizar(comentario));
    }
    /**
     * Lista todos los comentarios
     */
    @GetMapping
    public ResponseEntity<List<Comentario>> listar(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size ) {
        return ResponseEntity.ok(service.listar(page, size));
    }

    /**
     * Obtiene el comentario más destacado de un producto específico
     */
    @GetMapping("{productoId}/destacado")
    public ResponseEntity<Comentario> comentarioDestacado(@PathVariable(name = "productoId") int productoId) {
        return ResponseEntity.ok(service.listarDestacados(productoId));
    }

    /**
     * Elimina un comentario por su ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
