package com.modelo.dto;

import java.io.Serializable;

import com.modelo.entities.Endereco;
import com.modelo.enums.TipoEndereco;

public class EnderecoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String ruaLogra;
	private String cep;
	private String numero;
	private String complemento;
	private String bairro;
	private String uf;
	private String cidade;
	private TipoEndereco tipoEndereco;

	public EnderecoDTO() {
	}

	public EnderecoDTO(Long id, String ruaLogra, String cep, String numero, String complemento, String bairro,
			String uf, String cidade, TipoEndereco tipoEndereco) {
		this.id = id;
		this.ruaLogra = ruaLogra;
		this.cep = cep;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.uf = uf;
		this.cidade = cidade;
		this.tipoEndereco = tipoEndereco;
	}

	
	public EnderecoDTO(Endereco entity) {
		this.id = entity.getId();
		this.ruaLogra = entity.getRuaLogra();
		this.cep = entity.getCep();
		this.numero = entity.getNumero();
		this.complemento = entity.getComplemento();
		this.bairro = entity.getBairro();
		this.uf = entity.getUf();
		this.cidade = entity.getCidade();
		this.tipoEndereco = entity.getTipoEndereco();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRuaLogra() {
		return ruaLogra;
	}

	public void setRuaLogra(String ruaLogra) {
		this.ruaLogra = ruaLogra;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public TipoEndereco getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(TipoEndereco tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}


	
	
}

