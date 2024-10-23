package com.uni.foodstock.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.uni.foodstock.dto.CategoriaDTO;
import com.uni.foodstock.entidade.Categoria;
import com.uni.foodstock.repositories.CategoriaRepository;
import com.uni.foodstock.services.exceptions.DatabaseException;
import com.uni.foodstock.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoriaRepository repository;

	@Transactional(readOnly = true) 						/* Buscar por ID */
	public CategoriaDTO findById(Long id) {
		Categoria produto = repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Recurso não encontrado"));
		return new CategoriaDTO(produto);

	}

	@Transactional(readOnly = true) 						/* Buscar todos */
	public Page<CategoriaDTO> findAll(Pageable pageable) {
		Page<Categoria> result = repository.findAll(pageable);
		return result.map(x -> new CategoriaDTO(x));

	}

	@Transactional 											/* Inserir novo produto */
	public CategoriaDTO insert(CategoriaDTO dto) {

		Categoria entidade = new Categoria();
		copyDtoToEntity(dto, entidade);
		entidade = repository.save(entidade);
		return new CategoriaDTO(entidade);

	}

	@Transactional 											/* Inserir novo produto */
	public CategoriaDTO update(Long id, CategoriaDTO dto) {
		try {
			Categoria entidade = repository.getReferenceById(id);
			BeanUtils.copyProperties(entidade,dto,"id");
			entidade = repository.save(entidade);
			return new CategoriaDTO(entidade);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}

	}

	@Transactional(propagation = Propagation.SUPPORTS) 		/* Deletor por ID */
	public void delete(Long id) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
		try {
			repository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Falha de integridade referencial");
		}
	}



	private void copyDtoToEntity(CategoriaDTO dto, Categoria entidade) {
		entidade.setId(dto.getId());
		entidade.setNome(dto.getNome());
		entidade.setDescricao(dto.getDescricao());
		entidade.setImgUrl(dto.getImgUrl());

	}

}