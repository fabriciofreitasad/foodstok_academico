package com.uni.foodstock.dto;

import com.uni.foodstock.entidade.Usuario;

public class UsuarioDTO {

	private Long id;
	private String nome;
	private String email;
	private String senha;

	public UsuarioDTO(Long id, String nome, String email, String senha) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}
	
	public UsuarioDTO(Usuario entidade) {
		id = entidade.getId();
		nome = entidade.getNome();
		email = entidade.getEmail();
		senha = entidade.getSenha();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

}
