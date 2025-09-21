package com.soporte.app.infrastructure.adapter.out.repository;

import com.soporte.app.infrastructure.adapter.out.repository.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRepositorySupportProduct extends JpaRepository<ProductEntity, Integer> {
    @Query(value = "SELECT * FROM tienda_virtual.producto WHERE nombre LIKE %:texto% OR descripcion LIKE %:texto%", nativeQuery = true)
    List<ProductEntity> buscarPorNombreODescripcion(@Param("texto") String texto);

    @Query(value = "SELECT * FROM tienda_virtual.producto WHERE precio_unidad BETWEEN :startRange AND :endRange", nativeQuery = true)
    List<ProductEntity> findProductByPrice(@Param("startRange") Float startRange, @Param("endRange") Float endRange);
}
