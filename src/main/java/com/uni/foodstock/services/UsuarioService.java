package com.uni.foodstock.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.uni.foodstock.dto.UsuarioDTO;
import com.uni.foodstock.entidade.Usuario;
import com.uni.foodstock.repositories.UsuarioRepositori;
import com.uni.foodstock.services.exceptions.DatabaseException;
import com.uni.foodstock.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepositori repositori;

	@Transactional(readOnly = true) 						/* Buscar por ID */
	public UsuarioDTO findById(Long id) {
		Usuario produto = repositori.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Recurso não encontrado"));
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
		try {
			Usuario entidade = repositori.getReferenceById(id);
			copyDtoToEntity(dto, entidade);
			entidade = repositori.save(entidade);
			return new UsuarioDTO(entidade);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}

	}
	
	@Transactional(propagation = Propagation.SUPPORTS) 		/* Deletor por ID */
	public void delete(Long id) {
		if (!repositori.existsById(id)) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
		try {
	        	repositori.deleteById(id);    		
		}
	    	catch (DataIntegrityViolationException e) {
	        	throw new DatabaseException("Falha de integridade referencial");
	   	}
	}

	private void copyDtoToEntity(UsuarioDTO dto, Usuario entidade) {
		entidade.setNome(dto.getNome());
		entidade.setEmail(dto.getEmail());
		entidade.setSenha(dto.getSenha());

	}

}
