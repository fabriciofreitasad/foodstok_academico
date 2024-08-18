package com.uni.foodstock.services;

import com.uni.foodstock.dto.CategoriaDTO;
import com.uni.foodstock.entidade.Categoria;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.uni.foodstock.dto.ProdutoDTO;
import com.uni.foodstock.entidade.Produto;
import com.uni.foodstock.repositories.ProdutoRepositori;
import com.uni.foodstock.services.exceptions.DatabaseException;
import com.uni.foodstock.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepositori repositori;

	@Transactional(readOnly = true) 						/* Buscar por ID */
	public ProdutoDTO findById(Long id) {
		Produto produto = repositori.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Recurso não encontrado"));
		return new ProdutoDTO(produto);

	}

	@Transactional(readOnly = true) 						/* Buscar todos */
	public Page<ProdutoDTO> findAll(Pageable pageable) {
		Page<Produto> result = repositori.findAll(pageable);
		return result.map(x -> new ProdutoDTO(x));

	}

	@Transactional 											/* Inserir novo produto */
	public ProdutoDTO insert(ProdutoDTO dto) {

		Produto entidade = new Produto();
		copyDtoToEntity(dto, entidade);
		entidade = repositori.save(entidade);
		return new ProdutoDTO(entidade);

	}

	@Transactional 											/* Inserir novo produto */
	public ProdutoDTO update(Long id, ProdutoDTO dto) {
		try {
			Produto entidade = repositori.getReferenceById(id);
			BeanUtils.copyProperties(entidade, dto, "id");
			entidade = repositori.save(entidade);
			return new ProdutoDTO(entidade);
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



	private void copyDtoToEntity(ProdutoDTO dto, Produto entidade) {
		entidade.setNome(dto.getNome());
		entidade.setMarca(dto.getMarca());
		entidade.setImgUrl(dto.getImgUrl());
		entidade.setDescricao(dto.getDescricao());
		entidade.setPreco(dto.getPreco());
		entidade.getCategories().clear();
		for (CategoriaDTO catDto : dto.getCategories()) {
			Categoria cat = new Categoria();
			cat.setId(catDto.getId());
			entidade.getCategories().add(cat);
		}
	}

}