package com.unigoias.foodstock_v3.entidade;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_produto")
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    private String marca;
    private String imgUrl;
    
    @ManyToMany
    @JoinTable(name = "tb_produto_categoria",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private Set<Categoria> categorias = new HashSet<>();
    
    @ManyToOne
    @JoinColumn(name = "estoque_id")
    private Estoque estoque;
    
    public Produto() {    
    }

    public Produto(String nome, String marca, String imgUrl) {
        this.nome = nome;
        this.marca = marca;
        this.imgUrl = imgUrl;
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
    
    public String getImgUrl() {
        return imgUrl;
    }
    
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(Set<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    // Método para adicionar uma categoria ao produto
    public void adicionarCategoria(Categoria categoria) {
        this.categorias.add(categoria);
        categoria.getProdutos().add(this);
    }

    // Método para remover uma categoria do produto
    public void removerCategoria(Categoria categoria) {
        this.categorias.remove(categoria);
        categoria.getProdutos().remove(this);
    }
}
