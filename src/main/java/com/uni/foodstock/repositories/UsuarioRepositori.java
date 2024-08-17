package com.uni.foodstock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uni.foodstock.entidade.Usuario;

import java.util.Optional;

public interface UsuarioRepositori extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
