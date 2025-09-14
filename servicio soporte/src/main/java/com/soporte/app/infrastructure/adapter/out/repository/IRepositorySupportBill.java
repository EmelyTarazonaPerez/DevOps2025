package com.soporte.app.infrastructure.adapter.out.repository;

import com.soporte.app.infrastructure.adapter.out.repository.entity.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositorySupportBill extends JpaRepository<BillEntity, Integer> {

}
