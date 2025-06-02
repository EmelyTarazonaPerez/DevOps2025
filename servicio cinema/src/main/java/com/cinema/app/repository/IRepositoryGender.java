package com.cinema.app.repository;

import com.cinema.app.repository.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositoryGender extends JpaRepository<Gender, Integer> {
}
