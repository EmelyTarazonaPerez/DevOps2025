package com.soporte.app.infrastructure.repository;

import com.soporte.app.infrastructure.repository.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositorySupportCategory extends JpaRepository<CategoryEntity, Integer> {
    // This interface extends JpaRepository to provide CRUD operations for CategoryModel.
    // Additional custom query methods can be defined here if needed.
}
