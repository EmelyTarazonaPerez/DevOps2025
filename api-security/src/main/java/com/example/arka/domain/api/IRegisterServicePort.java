package com.example.arka.domain.api;


import com.example.arka.domain.model.Usuario;

public interface IRegisterServicePort {
    Usuario crearUsuario(Usuario user);
    Usuario buscarUsuarioPorEmail(String email);
}
