package com.uni.foodstock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uni.foodstock.entidade.Categoria;

public interface CategoriaRepositori extends JpaRepository<Categoria, Long> {

}
