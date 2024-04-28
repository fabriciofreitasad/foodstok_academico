package com.uni.foodstock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uni.foodstock.entidade.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Long>{

}
