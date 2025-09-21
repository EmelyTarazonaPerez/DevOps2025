package com.microservicio.comentarios.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "comentarios")
public class Comentario {
    @Id
    private String id;
    private String autor;
    private String mensaje;
    private int productoId;
    private int like;
    private LocalDateTime fecha;
}
