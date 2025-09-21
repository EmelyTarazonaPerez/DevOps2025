package com.soporte.app.infrastructure.adapter.out.repository;

import com.soporte.app.infrastructure.adapter.out.repository.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositorySupportSupplier extends JpaRepository<SupplierEntity, Integer> {
    // This interface extends JpaRepository to provide CRUD operations for SupplierEntity.
    // Additional custom query methods can be defined here if needed.
}
