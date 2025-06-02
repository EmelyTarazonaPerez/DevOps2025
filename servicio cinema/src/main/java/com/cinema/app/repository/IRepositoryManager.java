package com.cinema.app.repository;

import com.cinema.app.repository.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositoryManager extends JpaRepository<Manager, Integer> {
}
