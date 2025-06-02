package com.cinema.app.repository.implementation;

import com.cinema.app.repository.IRepositoryActor;
import com.cinema.app.repository.entity.Actor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RepositoryActor {
    private final IRepositoryActor repositoryActor;

    public Optional<Actor> findById(Integer id){
        return repositoryActor.findById(id);
    }
}
