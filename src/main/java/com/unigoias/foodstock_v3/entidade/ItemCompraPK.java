package com.unigoias.foodstock_v3.entidade;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class ItemCompraPK {
	
	
	@ManyToOne
	@JoinColumn(name = "compra_id")
	private ListaCompra compra;
	 
	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;
	 
	public ItemCompraPK() {
	}

	public ListaCompra getCompra() {
		return compra;
	}

	public void setCompra(ListaCompra compra) {
		this.compra = compra;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	 
	
}
