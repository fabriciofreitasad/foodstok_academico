package com.uni.foodstock.entidade;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

@Entity
@Table(name = "tb_produto")
@FilterDef(name="usuario", parameters = {@ParamDef(name = "emailusuario", type = String.class)})
@Filter(name="usuario", condition = "usuario_codigo = (select id from tb_usuario where email = :emailusuario)")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String marca;
	private BigDecimal preco;
	private String descricao;
	private BigDecimal quantidade;
	private String unidade;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date validade;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_produto_categoria", joinColumns = @JoinColumn(name = "produto_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
	private Set<Categoria> categories = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "usuario_codigo")
	private Usuario usuario;

	@Column(name = "tenant_id")
	private Long tenantId;


	public Produto() {
	}

	public Produto(Long id,String unidade,Date validade, String nome, String marca, BigDecimal preco, String descricao, BigDecimal quantidade, Set<Categoria> categories) {
		this.id = id;
		this.nome = nome;
		this.marca = marca;
		this.preco = preco;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.categories = categories;
		this.unidade = unidade;
		this.validade=validade;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public Set<Categoria> getCategories() {
		return categories;
	}

	public void setCategories(Set<Categoria> categories) {
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
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id);
	}

}