package com.soporte.app.infrastructure.repository;

import com.soporte.app.infrastructure.repository.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IRepositorySupportCard extends JpaRepository<CardEntity, Integer> {
    @Query("SELECT c FROM CardEntity c WHERE c.idClient = ?1")
    public List<CardEntity> findByCardByIdCustomer(Integer idCustomer);

    @Query("SELECT c FROM CardEntity c WHERE c.state = ?1")
    public List<CardEntity> getAbandonedCarts(String status);
}
