package com.uni.foodstock.dto;

import com.uni.foodstock.entidade.Estoque;
import com.uni.foodstock.entidade.Produto;

public class EstoqueDTO {
	
	private Long id;
	private Produto produto;
    private int quantidade;
    
	public EstoqueDTO() {
	}

	public EstoqueDTO(Estoque entidade) {
		id = entidade.getId();
		produto = entidade.getProduto();
		quantidade = entidade.getQuantidade();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	
    
    

}
