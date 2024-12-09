package com.uni.foodstock.security.dto;

import com.uni.foodstock.entities.Usuario;

public record ResponseDTO(Usuario user, String token) {
}
