package com.microservicio.comentarios.service;

import com.microservicio.comentarios.model.Comentario;
import com.microservicio.comentarios.model.ComentarioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComentarioService {

    private final ComentarioRepository repository;

    public Comentario crear(ComentarioDto comentario) {
        Comentario comentarioEntity = new Comentario();
        comentarioEntity.setAutor(comentario.getAutor());
        comentarioEntity.setMensaje(comentario.getMensaje());
        comentarioEntity.setFecha(LocalDateTime.now());
        comentarioEntity.setProductoId(comentario.getProductoId());
        comentarioEntity.setLike(0);
        return repository.save(comentarioEntity);
    }

    public Comentario actualizar(Comentario comentario) {
        return repository.save(comentario);
    }

    public List<Comentario> listar(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("fecha").descending());
        return repository.findAll(pageable).getContent();
    }

    public Comentario listarDestacados(int productoId) {
        List<Comentario> listaDestacado = repository.findByProductoId(productoId);
        return listaDestacado.stream().max((c1, c2) -> Integer.compare(c1.getLike(), c2.getLike()))
                .orElse(null);
    }

    public void eliminar(String id) {
        repository.deleteById(id);
    }
}
