package com.modelo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.modelo.entities.Endereco;

@Repository
public interface EnderecoRepositori extends JpaRepository<Endereco, Long> {

}