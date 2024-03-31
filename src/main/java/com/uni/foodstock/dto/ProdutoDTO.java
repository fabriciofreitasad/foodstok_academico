package com.uni.foodstock.dto;

import com.uni.foodstock.entidade.Produto;

public class ProdutoDTO {
	 
	private Long id;
    private String nome;
    private String marca;
    private String imgUrl;
    
	public ProdutoDTO() {
	}

	public ProdutoDTO(Long id, String nome, String marca, String imgUrl) {
		this.id = id;
		this.nome = nome;
		this.marca = marca;
		this.imgUrl = imgUrl;
	}
	
	public ProdutoDTO(Produto entidade) {
		id = entidade.getId();
		nome = entidade.getNome();
		marca = entidade.getMarca();
		imgUrl = entidade.getImgUrl();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getMarca() {
		return marca;
	}

	public String getImgUrl() {
		return imgUrl;
	}
	
	
    

}
