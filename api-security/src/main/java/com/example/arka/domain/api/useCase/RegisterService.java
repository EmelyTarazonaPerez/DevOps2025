package com.example.arka.domain.api.useCase;

import com.example.arka.domain.api.IRegisterServicePort;
import com.example.arka.domain.model.Usuario;
import com.example.arka.domain.spi.IPasswordEncodePort;
import com.example.arka.domain.spi.IUserPersistencePort;

public class RegisterService implements IRegisterServicePort {
    private final IUserPersistencePort repositorioUsuario;
    private final IPasswordEncodePort passwordEncoder;

    public RegisterService(IUserPersistencePort userPersistencePort, IPasswordEncodePort passwordEncodePort) {
        this.repositorioUsuario = userPersistencePort;
        this.passwordEncoder = passwordEncodePort;
    }

    @Override
    public Usuario crearUsuario(Usuario objetoUsuario) {
        if(!repositorioUsuario.existsByEmailAddress(objetoUsuario.getEmailAddress())) {
            objetoUsuario.setPassword(passwordEncoder.encodeToPasswordEncoder(objetoUsuario.getPassword())); //Encriptamos la contraseña
            return repositorioUsuario.save(objetoUsuario);
        } else {
            throw new RuntimeException("Usuario ya existe");
        }
    }

    @Override
    public Usuario buscarUsuarioPorEmail(String email) {
        return repositorioUsuario.findUserByEmailAddress(email);
    }

}
