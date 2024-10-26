package com.uni.foodstock.services;

import com.uni.foodstock.dto.CategoriaDTO;
import com.uni.foodstock.entities.Categoria;
import com.uni.foodstock.entities.Produto;
import com.uni.foodstock.dto.ProdutoDTO;
import com.uni.foodstock.repositories.ProdutoRepository;
import com.uni.foodstock.services.exceptions.DatabaseException;
import com.uni.foodstock.services.exceptions.ProdutoException;
import com.uni.foodstock.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	@Transactional(readOnly = true) /* Buscar por ID */
	public ProdutoDTO findById(Long id) {
		Produto produto = repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Recurso não encontrado"));
		return new ProdutoDTO(produto);
	}

	@Transactional(readOnly = true)
	public Page<ProdutoDTO> findAll(String nomeProduto, String nomeCategoria, Pageable pageable) {
		String tenantId = SecurityContextHolder.getContext().getAuthentication().getName(); // Obter o tenantId

		Page<Produto> result;

		// Verifica se há filtros e aplica a busca condicional
		if ((nomeProduto == null || nomeProduto.isEmpty()) && (nomeCategoria == null || nomeCategoria.isEmpty())) {
			result = repository.findByTenantId(tenantId, pageable); // Busca todos os produtos do tenant
		} else {
			result = repository.findByTenantIdAndNomeOuCategoria(tenantId, nomeProduto, nomeCategoria, pageable); // Aplica os filtros
		}

		return result.map(ProdutoDTO::new);
	}

	@Transactional /* Inserir novo produto */
	public ProdutoDTO insert(ProdutoDTO dto) {
		if (dto.getNome() == null || dto.getNome().isEmpty()) {
			if (dto.getCategories() == null || dto.getCategories().isEmpty()) {
				throw new ProdutoException("Erro ao inserir Produto! Nome ou Categoria vazios!");
			}
		}
		Produto entidade = new Produto();
		copyDtoToEntity(dto, entidade);

		// Obter o tenantId do usuário autenticado
		String tenantId = SecurityContextHolder.getContext().getAuthentication().getName();
		entidade.setTenantId(tenantId); // Definindo o tenantId

		entidade = repository.save(entidade);
		return new ProdutoDTO(entidade);
	}

	@Transactional /* Atualizar produto */
	public ProdutoDTO update(Long id, ProdutoDTO dto) {
		try {
			Produto entidade = repository.getReferenceById(id);
			BeanUtils.copyProperties(entidade, dto, "id");
			entidade = repository.save(entidade);
			return new ProdutoDTO(entidade);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS) /* Deletar por ID */
	public void delete(Long id) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Falha de integridade referencial");
		}
	}

	@Transactional(readOnly = true)
	public Page<ProdutoDTO> listProductsCategory(String nomeCategoria, Pageable pageable) {
		String tenantId = SecurityContextHolder.getContext().getAuthentication().getName(); // Obter o tenantId
		Page<Produto> produtos = repository.findByTenantIdAndCategoriaNome(tenantId, nomeCategoria, pageable);

		return produtos.map(p -> {
			ProdutoDTO produtoDTO = new ProdutoDTO();
			produtoDTO.setId(p.getId());
			produtoDTO.setPreco(p.getPreco());
			produtoDTO.setNome(p.getNome());
			produtoDTO.setDescricao(p.getDescricao());
			produtoDTO.setQuantidade(p.getQuantidade());
			produtoDTO.setUnidade(p.getUnidade());
			produtoDTO.setMarca(p.getMarca());
			produtoDTO.setValidade(p.getValidade());

			Set<CategoriaDTO> categoriaDTOs = p.getCategories().stream()
					.map(CategoriaDTO::new)
					.collect(Collectors.toSet());
			produtoDTO.setCategories(categoriaDTOs);

			return produtoDTO;
		});
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
