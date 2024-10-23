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
import com.uni.foodstock.repositories.ProdutoRepository;
import com.uni.foodstock.services.exceptions.DatabaseException;
import com.uni.foodstock.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	@Transactional(readOnly = true)
	public ProdutoDTO findById(Long id, Long tenantId) {
		Produto produto = repository.findByIdAndTenantId(id, String.valueOf(tenantId)).orElseThrow(
				() -> new ResourceNotFoundException("Recurso não encontrado"));
		return new ProdutoDTO(produto);
	}

	@Transactional(readOnly = true)
	public Page<ProdutoDTO> findAll(String nomeProduto, String nomeCategoria, Long tenantId, Pageable pageable) {
		Page<Produto> result;

		if ((nomeProduto == null || nomeProduto.isEmpty()) && (nomeCategoria == null || nomeCategoria.isEmpty())) {
			result = repository.findByTenantId(String.valueOf(tenantId), pageable); // Busca todos os produtos do tenant
		} else {
			result = repository.findByNomeOuCategoriaAndTenantId(nomeProduto, nomeCategoria, String.valueOf(tenantId), pageable); // Aplica os filtros
		}

		return result.map(ProdutoDTO::new);
	}

	@Transactional
	public ProdutoDTO insert(ProdutoDTO dto, Long tenantId) {
		Produto entidade = new Produto();
		copyDtoToEntity(dto, entidade);
		entidade.setTenantId(tenantId); // Define o tenantId no produto
		entidade = repository.save(entidade);
		return new ProdutoDTO(entidade);
	}

	@Transactional
	public ProdutoDTO update(Long id, ProdutoDTO dto, Long tenantId) {
		try {
			Produto entidade = repository.findByIdAndTenantId(id, String.valueOf(tenantId)).orElseThrow(
					() -> new ResourceNotFoundException("Recurso não encontrado"));
			BeanUtils.copyProperties(entidade, dto, "id");
			entidade = repository.save(entidade);
			return new ProdutoDTO(entidade);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id, Long tenantId) {
		if (!repository.existsByIdAndTenantId(id, tenantId)) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Falha de integridade referencial");
		}
	}

	private void copyDtoToEntity(ProdutoDTO dto, Produto entidade) {
		entidade.setNome(dto.getNome());
		entidade.setMarca(dto.getMarca());
		entidade.setQuantidade(dto.getQuantidade());
		entidade.setDescricao(dto.getDescricao());
		entidade.setPreco(dto.getPreco());
		entidade.setUnidade(dto.getUnidade());
		entidade.setValidade(dto.getValidade());
		entidade.getCategories().clear();
		for (CategoriaDTO catDto : dto.getCategories()) {
			Categoria cat = new Categoria();
			cat.setId(catDto.getId());
			entidade.getCategories().add(cat);
		}
	}
}
