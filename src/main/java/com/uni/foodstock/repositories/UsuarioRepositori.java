package com.uni.foodstock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uni.foodstock.entidade.Usuario;

public interface UsuarioRepositori extends JpaRepository<Usuario, Long> {

}
