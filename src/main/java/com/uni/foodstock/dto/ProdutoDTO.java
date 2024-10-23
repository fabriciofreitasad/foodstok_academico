package com.uni.foodstock.dto;

import java.math.BigDecimal;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.uni.foodstock.entidade.Categoria;
import com.uni.foodstock.entidade.Produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProdutoDTO {

	private Long id;

	@Size(min = 3, max = 80, message = "Nome presisa ter de 3 a 80 caracteres")
	@NotBlank(message = "Campo requerido")
	private String nome;

	@Size(min = 2, max = 80, message = "Nome presisa ter de 2 a 80 caracteres")
	private String marca;

	@Positive(message = "O preço deve ser positivo")
	private BigDecimal preco;

	@Size(min = 10, message = "Descrição precisa ter no minimom 10 caracteres")
	private String descricao;
	private BigDecimal quantidade;
	private String unidade;
	private Date validade;


	@NotEmpty(message = "Deve ter uma categoria")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Set<CategoriaDTO> categories = new HashSet<>();

	public ProdutoDTO(Long id,String unidade ,Date validade,String nome, BigDecimal preco, String marca, String descricao, BigDecimal quantidade, Set<CategoriaDTO> categories) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.marca = marca;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.categories = categories;
		this.unidade = unidade;
		this.validade = validade;
	}

	public ProdutoDTO(Produto entidade) {
		id = entidade.getId();
		nome = entidade.getNome();
		marca = entidade.getMarca();
		descricao = entidade.getDescricao();
		preco = entidade.getPreco();
		quantidade = entidade.getQuantidade();
		unidade = entidade.getUnidade();
		validade = entidade.getValidade();

		for (Categoria cat : entidade.getCategories()) {
			categories.add(new CategoriaDTO(cat));
		}
	}

	public ProdutoDTO() {

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

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public Set<CategoriaDTO> getCategories() {
		return categories;
	}

	public void setCategories(Set<CategoriaDTO> categories) {
		this.categories = categories;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public Date getValidade() {
		return validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}
}