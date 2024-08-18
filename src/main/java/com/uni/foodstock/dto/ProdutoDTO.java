package com.uni.foodstock.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.uni.foodstock.entidade.Categoria;
import com.uni.foodstock.entidade.Produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProdutoDTO {

	private Long id;

	@Size(min = 3, max = 80, message = "Nome presisa ter de 3 a 80 caracteres")
	@NotBlank(message = "Campo requerido")
	private String nome;

	@Size(min = 3, max = 80, message = "Nome presisa ter de 3 a 80 caracteres")
	@NotBlank(message = "Campo requerido")
	private String marca;

	@NotNull(message = "Campo requerido")
	@Positive(message = "O preço deve ser positivo")
	private BigDecimal preco;

	@Size(min = 10, message = "Descrição precisa ter no minimom 10 caracteres")
	@NotBlank(message = "Campo requerido")
	private String descricao;
	private String imgUrl;


	@NotEmpty(message = "Deve ter pelo menos uma categoria")
	private Set<CategoriaDTO> categories = new HashSet<>();

	public ProdutoDTO(Long id, String nome, String marca, BigDecimal preco, String descricao, String imgUrl) {
		this.id = id;
		this.nome = nome;
		this.marca = marca;
		this.preco = preco;
		this.descricao = descricao;
		this.imgUrl = imgUrl;
	}

	public ProdutoDTO(Produto entidade) {
		id = entidade.getId();
		nome = entidade.getNome();
		marca = entidade.getMarca();
		descricao = entidade.getDescricao();
		preco = entidade.getPreco();
		imgUrl = entidade.getImgUrl();

		for (Categoria cat : entidade.getCategories()) {
			categories.add(new CategoriaDTO(cat));
		}
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

	public Set<CategoriaDTO> getCategories() {
		return categories;
	}

	public void setCategories(Set<CategoriaDTO> categories) {
		this.categories = categories;
	}


}