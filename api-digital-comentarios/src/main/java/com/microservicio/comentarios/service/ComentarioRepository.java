package com.microservicio.comentarios.service;
import com.microservicio.comentarios.model.Comentario;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ComentarioRepository extends MongoRepository<Comentario, String>{
    Comentario findTopByOrderByLikeDesc();
    List<Comentario> findByProductoId(int productoId);
}
