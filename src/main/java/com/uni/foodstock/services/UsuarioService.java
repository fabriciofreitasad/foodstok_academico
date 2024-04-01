package com.uni.foodstock.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uni.foodstock.dto.UsuarioDTO;
import com.uni.foodstock.entidade.Usuario;
import com.uni.foodstock.repositories.UsuarioRepositori;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepositori repositori;

	@Transactional(readOnly = true) 						/* Buscar por ID */
	public UsuarioDTO findById(Long id) {
		Usuario produto = repositori.findById(id).get();
		return new UsuarioDTO(produto);

	}

	@Transactional(readOnly = true) 						/* Buscar todos */
	public Page<UsuarioDTO> findAll(Pageable pageable) {
		Page<Usuario> result = repositori.findAll(pageable);
		return result.map(x -> new UsuarioDTO(x));

	}

	@Transactional 											/* Inserir novo usuario */
	public UsuarioDTO insert(UsuarioDTO dto) {

		Usuario entidade = new Usuario();
		copyDtoToEntity(dto, entidade);
		entidade = repositori.save(entidade);
		return new UsuarioDTO(entidade);

	}

	@Transactional 											/* Inserir novo usuario */
	public UsuarioDTO update(Long id, UsuarioDTO dto) {

		Usuario entidade = repositori.getReferenceById(id);
		copyDtoToEntity(dto, entidade);
		entidade = repositori.save(entidade);
		return new UsuarioDTO(entidade);

	}
	
	@Transactional 											/* Deletor por ID */
	public void delete(Long id) {
		repositori.deleteById(id);
	}

	private void copyDtoToEntity(UsuarioDTO dto, Usuario entidade) {
		entidade.setNome(dto.getNome());
		entidade.setEmail(dto.getEmail());
		entidade.setSenha(dto.getSenha());

	}

}
