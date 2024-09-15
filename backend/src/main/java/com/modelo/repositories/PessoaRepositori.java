package com.modelo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.modelo.entities.Pessoa;

@Repository
public interface PessoaRepositori extends JpaRepository<Pessoa, Long> {

}
