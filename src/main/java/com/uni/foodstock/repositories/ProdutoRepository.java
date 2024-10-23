package com.uni.foodstock.repositories;

import com.uni.foodstock.entidade.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("SELECT p FROM Produto p WHERE p.id = :id AND p.tenantId = :tenantId")
    Optional<Produto> findByIdAndTenantId(@Param("id") Long id, @Param("tenantId") String tenantId);

    @Query("SELECT p FROM Produto p WHERE p.tenantId = :tenantId")
    Page<Produto> findByTenantId(@Param("tenantId") String tenantId, Pageable pageable);

    @Query("SELECT p FROM Produto p JOIN p.categories c WHERE (:nomeProduto IS NULL OR p.nome LIKE %:nomeProduto%) AND (:nomeCategoria IS NULL OR c.nome = :nomeCategoria) AND p.tenantId = :tenantId")
    Page<Produto> findByNomeOuCategoriaAndTenantId(@Param("nomeProduto") String nomeProduto, @Param("nomeCategoria") String nomeCategoria, @Param("tenantId") String tenantId, Pageable pageable);

    @Query("SELECT p FROM Produto p JOIN p.categories c WHERE c.nome = :nomeCategoria AND p.tenantId = :tenantId")
    Page<Produto> findByCategoriaNomeAndTenantId(@Param("nomeCategoria") String nomeCategoria, @Param("tenantId") String tenantId, Pageable pageable);

    boolean existsByIdAndTenantId(Long id, Long tenantId);
}
