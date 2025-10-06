package com.example.arka.adapters.driven.jpa.msql.repository;

import com.example.arka.adapters.driven.jpa.msql.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepositoryJPA extends JpaRepository<UserEntity, Integer> {
    boolean existsByEmailAddress(String emailUsuario);
    UserEntity findUserByEmailAddress(String emailUsuario);
}
