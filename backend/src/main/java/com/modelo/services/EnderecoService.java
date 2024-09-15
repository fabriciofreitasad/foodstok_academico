package com.modelo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.modelo.dto.EnderecoDTO;
import com.modelo.entities.Endereco;
import com.modelo.repositories.EnderecoRepositori;
import com.modelo.services.exceptions.DatabaseException;
import com.modelo.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepositori repositori;
	

	@Transactional(readOnly = true)
	public Page<EnderecoDTO> findAllPaged(Pageable pageable) {
		Page<Endereco> list = repositori.findAll(pageable);
		return list.map(x -> new EnderecoDTO(x));
	}

	@Transactional(readOnly = true)
	public EnderecoDTO findById(Long id) {
		Optional<Endereco> obj = repositori.findById(id);
		Endereco entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade não encontrada"));
		return new EnderecoDTO(entity);
	}

	@Transactional
	public EnderecoDTO insert(EnderecoDTO dto) {
		Endereco entity = new Endereco();
		copyDtoToEntity(dto, entity);
		entity = repositori.save(entity);
		return new EnderecoDTO(entity);

	}

	@Transactional
	public EnderecoDTO update(Long id, EnderecoDTO dto) {
		try {
			Endereco entity = repositori.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repositori.save(entity);
			return new EnderecoDTO(entity);
			
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("ID não encontrado! " + id);
		}
	}


	@Transactional(propagation = Propagation.SUPPORTS)
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

	private void copyDtoToEntity(EnderecoDTO dto, Endereco entity) {
		entity.setRuaLogra(dto.getRuaLogra());
		entity.setCep(dto.getCep());
		entity.setNumero(dto.getNumero());
		entity.setComplemento(dto.getComplemento());
		entity.setBairro(dto.getBairro());
		entity.setUf(dto.getUf());
		entity.setCidade(dto.getCidade());
		entity.setTipoEndereco(dto.getTipoEndereco());
						
	}

}

