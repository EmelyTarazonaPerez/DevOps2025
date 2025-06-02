package com.cinema.app.repository;

import com.cinema.app.repository.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRepositoryMovie extends JpaRepository<MovieEntity, Integer> {
    List<MovieEntity> findAllByManagerId(Integer managerId);
    List<MovieEntity> findAllByGenderId(Integer gender);
}
