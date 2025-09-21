package com.microservicio.comentarios.model;

import lombok.Data;

@Data
public class ComentarioDto {
    private String autor;
    private int productoId;
    private String mensaje;
}
