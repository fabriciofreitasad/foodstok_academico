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
import com.modelo.dto.PessoaDTO;
import com.modelo.entities.Endereco;
import com.modelo.entities.Pessoa;
import com.modelo.repositories.EnderecoRepositori;
import com.modelo.repositories.PessoaRepositori;
import com.modelo.services.exceptions.DatabaseException;
import com.modelo.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepositori repositori;

	@Autowired
	private EnderecoRepositori enderecoRepositori;

	@Transactional(readOnly = true)
	public Page<PessoaDTO> findAllPaged(Pageable pageable) {
		Page<Pessoa> list = repositori.findAll(pageable);
		return list.map(x -> new PessoaDTO(x));
	}

	@Transactional(readOnly = true)
	public PessoaDTO findById(Long id) {
		Optional<Pessoa> obj = repositori.findById(id);
		Pessoa entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade não encontrada"));
		return new PessoaDTO(entity,entity.getEnderecos());
	}

	@Transactional
	public PessoaDTO insert(PessoaDTO dto) {
		Pessoa entity = new Pessoa();
		copyDtoToEntity(dto, entity);
		entity = repositori.save(entity);
		return new PessoaDTO(entity);

	}

	@Transactional
	public PessoaDTO update(Long id, PessoaDTO dto) {
		try {
			Pessoa entity = repositori.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repositori.save(entity);
			return new PessoaDTO(entity);

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
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Falha de integridade referencial");
		}
	}

	private void copyDtoToEntity(PessoaDTO dto, Pessoa entity) {
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		entity.setTelefone(dto.getTelefone());
		entity.setTipoPessoa(dto.getTipoPessoa());

		entity.getEnderecos().clear();
		for (EnderecoDTO endDto : dto.getEnderecos()) {
			Endereco endereco = enderecoRepositori.getReferenceById(endDto.getId());
			entity.getEnderecos().add(endereco);
		}

	}

}

