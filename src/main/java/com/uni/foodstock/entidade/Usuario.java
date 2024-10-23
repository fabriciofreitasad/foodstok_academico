package com.uni.foodstock.entidade;

import java.util.Collection;
import java.util.Objects;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	@Column(unique = true)
	private String email;

	private String senha;

	public Usuario() {
	}

	public Usuario(Long id, String nome, String email, String senha) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	// Métodos da interface UserDetails

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// Se você tiver papéis ou permissões, deve retornar aqui
		return null;  // Sem roles ou authorities por enquanto
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;  // Assumindo que a conta nunca expira
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;  // Assumindo que a conta nunca é bloqueada
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;  // Assumindo que as credenciais nunca expiram
	}

	@Override
	public boolean isEnabled() {
		return true;  // Assumindo que a conta está sempre habilitada
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
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}
}
