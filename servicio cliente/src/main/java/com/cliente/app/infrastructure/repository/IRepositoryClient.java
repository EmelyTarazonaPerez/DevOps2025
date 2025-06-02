package com.cinema.app.infrastructure.repository;

import com.cinema.app.infrastructure.repository.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositoryClient extends JpaRepository<ClientEntity, Integer> {
}
