package com.soporte.app.infrastructure.adapter.out.repository;

import com.soporte.app.infrastructure.adapter.out.repository.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositoryClient extends JpaRepository<ClientEntity, Integer> {
    ClientEntity findByName(String name);
}
