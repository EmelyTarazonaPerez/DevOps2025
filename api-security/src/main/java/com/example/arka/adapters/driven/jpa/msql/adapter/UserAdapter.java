package com.example.arka.adapters.driven.jpa.msql.adapter;

import com.example.arka.adapters.driven.jpa.msql.entity.UserEntity;
import com.example.arka.adapters.driven.jpa.msql.mapper.IUserEntityMapper;
import com.example.arka.adapters.driven.jpa.msql.repository.IUserRepositoryJPA;
import com.example.arka.domain.model.Usuario;
import com.example.arka.domain.spi.IUserPersistencePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserAdapter implements IUserPersistencePort {

    private IUserRepositoryJPA userRepositoryJPA;
    private IUserEntityMapper userEntityMapper;

    @Override
    public boolean existsByEmailAddress(String emailUsuario) {
        if (userRepositoryJPA.existsByEmailAddress(emailUsuario)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Usuario findUserByEmailAddress(String emailUsuario) {
        if (this.existsByEmailAddress(emailUsuario)) {
            UserEntity userEntity = userRepositoryJPA.findUserByEmailAddress(emailUsuario);
            return userEntityMapper.toUser(userEntity);
        }
        return null;
    }

    @Override
    public Usuario save(Usuario user) {
        UserEntity userEntity = userEntityMapper.toUserEntity(user);
        return userEntityMapper.toUser(userRepositoryJPA.save(userEntity));
    }

}
