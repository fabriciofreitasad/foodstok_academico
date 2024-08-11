package com.uni.foodstock.dto;

import com.uni.foodstock.entidade.Categoria;

public class CategoriaDTO {

	private Long id;
	
	
	private String nome;
	
	
	private String descricao;

	public CategoriaDTO() {
	}

	public CategoriaDTO(Categoria entidade) {
		id = entidade.getId();
		nome = entidade.getNome();
		descricao = entidade.getDescricao();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
