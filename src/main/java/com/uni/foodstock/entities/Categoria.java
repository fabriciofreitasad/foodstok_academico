package com.uni.foodstock.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_categoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String descricao;
	@Lob
	@Column(name = "cert_file", length = 65535)
	private byte[] imagem;
	private String tenantId;

	@ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
	private Set<Produto> produtos = new HashSet<>();

	public Categoria() {
	}

	public Categoria(Long id, String tenantId, String nome, String descricao, byte[] imagem, Set<Produto> produtos) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.imagem = imagem;
		this.tenantId = tenantId;
		this.produtos = produtos;
	}

	// Getters e Setters

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

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public Set<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(Set<Produto> produtos) {
		this.produtos = produtos;
	}

	// Métodos de comparação (hashCode e equals)

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
		Categoria other = (Categoria) obj;
		return Objects.equals(id, other.id);
	}
}
