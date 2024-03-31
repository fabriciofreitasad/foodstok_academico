package com.uni.foodstock.entidade;

import java.util.Objects;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_compra_item")
public class ItemCompra {

	@EmbeddedId
	private ItemCompraPK id = new ItemCompraPK();

	private Integer quantidade;

	public ItemCompra() {
	}

	public ItemCompra(ListaCompra listaCompras, Produto produto, Integer quantidade) {
		id.setProduto(produto);
		id.setCompra(listaCompras);
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return id.getProduto();
	}

	public void setProduto(Produto produto) {
		id.setProduto(produto);
	}

	public void setListaCompra(ListaCompra listaCompra) {
		id.setCompra(listaCompra);
	}

	public ListaCompra getListaCompra() {
		return id.getCompra();
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemCompra other = (ItemCompra) obj;
		return Objects.equals(id, other.id);
	}

}
