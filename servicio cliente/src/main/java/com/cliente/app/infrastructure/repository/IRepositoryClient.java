package com.cliente.app.infrastructure.repository;

import com.cliente.app.infrastructure.repository.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositoryClient extends JpaRepository<ClientEntity, Integer> {
    ClientEntity findByName(String name);
}
