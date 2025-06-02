package com.cinema.app.repository.implementation;

import com.cinema.app.repository.IRepositoryManager;
import com.cinema.app.repository.entity.Manager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RepositoryManager  {
    private final IRepositoryManager repositoryManager;

    public Optional<Manager> findById (Integer id){
        return repositoryManager.findById(id);
    }
}
