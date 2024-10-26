package com.uni.foodstock.repositories;

import com.uni.foodstock.entities.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("SELECT p FROM Produto p WHERE p.tenantId = :tenantId")
    Page<Produto> findByTenantId(@Param("tenantId") String tenantId, Pageable pageable);

    @Query("SELECT p FROM Produto p WHERE p.tenantId = :tenantId AND (:nomeProduto IS NULL OR p.nome LIKE %:nomeProduto%) AND (:nomeCategoria IS NULL OR EXISTS (SELECT c FROM p.categories c WHERE c.nome = :nomeCategoria))")
    Page<Produto> findByTenantIdAndNomeOuCategoria(@Param("tenantId") String tenantId, @Param("nomeProduto") String nomeProduto, @Param("nomeCategoria") String nomeCategoria, Pageable pageable);

    @Query("SELECT p FROM Produto p JOIN p.categories c WHERE p.tenantId = :tenantId AND c.nome = :nomeCategoria")
    Page<Produto> findByTenantIdAndCategoriaNome(@Param("tenantId") String tenantId, @Param("nomeCategoria") String nomeCategoria, Pageable pageable);
}
