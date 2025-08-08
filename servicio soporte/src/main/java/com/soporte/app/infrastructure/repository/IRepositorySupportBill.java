package com.soporte.app.infrastructure.repository;

import com.soporte.app.infrastructure.repository.entity.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositorySupportBill extends JpaRepository<BillEntity, Integer> {

}
