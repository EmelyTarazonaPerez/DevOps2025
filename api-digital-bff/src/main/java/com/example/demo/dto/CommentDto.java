package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public class CommentDto {
    @JsonProperty("id")
    private String id;
    @JsonProperty("autor")
    private String autor;
    @JsonProperty("productoId")
    private int productoId;
    @JsonProperty("mensaje")
    private String mensaje;
    @JsonProperty("like")
    private int like;
    @JsonProperty("fecha")
    private LocalDateTime fecha;

    public CommentDto(String id, String autor, int productoId, String mensaje, int like, LocalDateTime fecha) {
        this.id = id;
        this.autor = autor;
        this.productoId = productoId;
        this.mensaje = mensaje;
        this.like = like;
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public CommentDto setId(String id) {
        this.id = id;
        return this;
    }

    public String getAutor() {
        return autor;
    }

    public CommentDto setAutor(String autor) {
        this.autor = autor;
        return this;
    }

    public int getProductoId() {
        return productoId;
    }

    public CommentDto setProductoId(int productoId) {
        this.productoId = productoId;
        return this;
    }

    public String getMensaje() {
        return mensaje;
    }

    public CommentDto setMensaje(String mensaje) {
        this.mensaje = mensaje;
        return this;
    }

    public int getLike() {
        return like;
    }

    public CommentDto setLike(int like) {
        this.like = like;
        return this;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public CommentDto setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
        return this;
    }
}
