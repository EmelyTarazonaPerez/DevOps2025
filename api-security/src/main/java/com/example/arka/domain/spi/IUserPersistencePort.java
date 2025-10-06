package com.example.arka.domain.spi;
import com.example.arka.domain.model.Usuario;

public interface IUserPersistencePort {
    boolean existsByEmailAddress(String emailUsuario);
    Usuario findUserByEmailAddress(String emailUsuario);
    Usuario save(Usuario user);
}
