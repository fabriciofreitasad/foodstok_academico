package com.uni.foodstock.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.uni.foodstock.dto.EstoqueDTO;
import com.uni.foodstock.entidade.Estoque;
import com.uni.foodstock.repositories.EstoqueRepository;
import com.uni.foodstock.services.exceptions.DatabaseException;
import com.uni.foodstock.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EstoqueService {
	
	@Autowired
	private EstoqueRepository estoqueRepository;
	
	@Transactional(readOnly = true) 						/* Buscar por ID */
	public EstoqueDTO findById(Long id) {
		Estoque estoque = estoqueRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Recurso não encontrado"));
		return new EstoqueDTO(estoque);

	}

	@Transactional(readOnly = true) 						/* Buscar todos */
	public Page<EstoqueDTO> findAll(Pageable pageable) {
		Page<Estoque> result = estoqueRepository.findAll(pageable);
		return result.map(x -> new EstoqueDTO(x));

	}

	@Transactional 											/* Inserir novo produto */
	public EstoqueDTO insert(EstoqueDTO dto) {

		Estoque entidade = new Estoque();
		copyDtoToEntity(dto, entidade);
		entidade = estoqueRepository.save(entidade);
		return new EstoqueDTO(entidade);

	}

	@Transactional 											/* Inserir novo produto */
	public EstoqueDTO update(Long id, EstoqueDTO dto) {
		try {
			Estoque entidade = estoqueRepository.getReferenceById(id);
			copyDtoToEntity(dto, entidade);
			entidade = estoqueRepository.save(entidade);
			return new EstoqueDTO(entidade);
		} 
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}

	}
	
	@Transactional(propagation = Propagation.SUPPORTS) 		/* Deletor por ID */
	public void delete(Long id) {
		if (!estoqueRepository.existsById(id)) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
		try {
			estoqueRepository.deleteById(id);    		
		}
	    	catch (DataIntegrityViolationException e) {
	        	throw new DatabaseException("Falha de integridade referencial");
	   	}
	}



	private void copyDtoToEntity(EstoqueDTO dto, Estoque entidade) {
		entidade.setProduto(dto.getProduto());
		entidade.setQuantidade(dto.getQuantidade());

	}
}
