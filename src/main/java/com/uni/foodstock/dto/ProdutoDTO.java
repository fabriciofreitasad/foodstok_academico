package com.uni.foodstock.dto;

import java.math.BigDecimal;

import com.uni.foodstock.entidade.Categoria;
import com.uni.foodstock.entidade.Produto;

public class ProdutoDTO {

	private Long id;
	private String nome;
	private String marca;
	private BigDecimal preco;
	private String descricao;
	private String imgUrl;
	private Categoria categoria;
	private Integer estoque;

	public ProdutoDTO(Long id, String nome, String marca, BigDecimal preco, String descricao, String imgUrl,
			Categoria categoria, Integer estoque) {
		this.id = id;
		this.nome = nome;
		this.marca = marca;
		this.preco = preco;
		this.descricao = descricao;
		this.imgUrl = imgUrl;
		this.categoria = categoria;
		this.estoque = estoque;
	}

	public ProdutoDTO(Produto entidade) {
		id = entidade.getId();
		nome = entidade.getNome();
		marca = entidade.getMarca();
		preco = entidade.getPreco();
		descricao = entidade.getDescricao();
		imgUrl = entidade.getImgUrl();
		categoria = entidade.getCategoria();
		estoque = entidade.getEstoque();
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

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

}
