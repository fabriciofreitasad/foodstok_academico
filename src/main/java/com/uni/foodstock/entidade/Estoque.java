package com.unigoias.foodstock_v3.entidade;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_estoque")
public class Estoque {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	public Estoque() {
	}
	
	public Estoque(Long id, List<Produto> produtos) {
		this.id = id;
		this.produtos = produtos;
	}
	
	@OneToMany(mappedBy = "estoque")
    private List<Produto> produtos = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}
}
