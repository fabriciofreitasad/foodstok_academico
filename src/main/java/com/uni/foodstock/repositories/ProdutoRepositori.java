package com.uni.foodstock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uni.foodstock.entidade.Produto;

public interface ProdutoRepositori extends JpaRepository<Produto, Long> {

}
