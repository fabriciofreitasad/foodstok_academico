package com.uni.foodstock.repositories;

import com.uni.foodstock.entities.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query("SELECT c FROM Categoria c WHERE c.tenantId = :tenantId OR c.tenantId IS NULL")
    Page<Categoria> findByTenantId(@Param("tenantId") String tenantId, Pageable pageable);

    @Query("SELECT c FROM Categoria c WHERE c.id = :id AND (c.tenantId = :tenantId OR c.tenantId IS NULL)")
    Optional<Categoria> findByIdAndTenantId(@Param("id") Long id, @Param("tenantId") String tenantId);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM Categoria c WHERE c.id = :id AND c.tenantId = :tenantId")
    boolean existsByIdAndTenantId(@Param("id") Long id, @Param("tenantId") String tenantId);
}
