package com.cinema.app.repository.implementation;

import com.cinema.app.repository.IRepositoryGender;
import com.cinema.app.repository.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RepositoryGender {
    private final IRepositoryGender repositoryGender;

    public Optional<Gender> findById (Integer id){
        return repositoryGender.findById(id);
    }
}
