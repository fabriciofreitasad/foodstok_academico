package com.modelo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.modelo.entities.Endereco;
import com.modelo.entities.Pessoa;

public class PessoaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String email;
	private String telefone;
	private String tipoPessoa;

	private List<EnderecoDTO> enderecos = new ArrayList<>();

	public PessoaDTO() {
	}

	public PessoaDTO(Long id, String name, String email, String telefone, String tipoPessoa) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.telefone = telefone;
		this.tipoPessoa = tipoPessoa;
	}

	public PessoaDTO(Pessoa entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.email = entity.getEmail();
		this.telefone = entity.getTelefone();
		this.tipoPessoa = entity.getTipoPessoa();
	}

	public PessoaDTO(Pessoa entity, List<Endereco> enderecos) {
	    this(entity);  // Chama o construtor básico
	    enderecos.forEach(end -> this.enderecos.add(new EnderecoDTO(end)));  // Mapeia os endereços
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public List<EnderecoDTO> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<EnderecoDTO> enderecos) {
		this.enderecos = enderecos;
	}

}

