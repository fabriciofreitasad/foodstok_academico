package com.modelo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.modelo.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
