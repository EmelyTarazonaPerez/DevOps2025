package com.cinema.app.repository;

import com.cinema.app.repository.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositoryActor extends JpaRepository<Actor, Integer> {
}
