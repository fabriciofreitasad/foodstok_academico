package com.uni.foodstock.services;

import com.uni.foodstock.dto.CategoriaDTO;
import com.uni.foodstock.entities.Categoria;
import com.uni.foodstock.repositories.CategoriaRepository;
import com.uni.foodstock.services.exceptions.DatabaseException;
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

@Service
public class CategoryService {

	@Autowired
	private CategoriaRepository repository;

	@Transactional(readOnly = true) /* Buscar por ID */
	public CategoriaDTO findById(Long id) {
		String tenantId = SecurityContextHolder.getContext().getAuthentication().getName(); // Obter o tenantId
		Categoria categoria = repository.findByIdAndTenantId(id, tenantId).orElseThrow(
				() -> new ResourceNotFoundException("Recurso não encontrado"));
		return new CategoriaDTO(categoria);
	}

	@Transactional(readOnly = true) /* Buscar todos */
	public Page<CategoriaDTO> findAll(Pageable pageable) {
		String tenantId = SecurityContextHolder.getContext().getAuthentication().getName(); // Obter o tenantId
		Page<Categoria> result = repository.findByTenantId(tenantId, pageable); // Buscar categorias filtradas pelo tenant
		return result.map(CategoriaDTO::new);
	}

	@Transactional /* Inserir nova categoria */
	public CategoriaDTO insert(CategoriaDTO dto) {
		Categoria entidade = new Categoria();
		copyDtoToEntity(dto, entidade);

		String tenantId = SecurityContextHolder.getContext().getAuthentication().getName(); // Obter o tenantId
		entidade.setTenantId(tenantId); // Definir o tenantId

		entidade = repository.save(entidade);
		return new CategoriaDTO(entidade);
	}

	@Transactional /* Atualizar categoria */
	public CategoriaDTO update(Long id, CategoriaDTO dto) {
		try {
			String tenantId = SecurityContextHolder.getContext().getAuthentication().getName(); // Obter o tenantId
			Categoria entidade = repository.findByIdAndTenantId(id, tenantId).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
			BeanUtils.copyProperties(dto, entidade, "id", "tenantId");
			entidade = repository.save(entidade);
			return new CategoriaDTO(entidade);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS) /* Deletar por ID */
	public void delete(Long id) {
		String tenantId = SecurityContextHolder.getContext().getAuthentication().getName(); // Obter o tenantId
		if (!repository.existsByIdAndTenantId(id, tenantId)) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Falha de integridade referencial");
		}
	}

	private void copyDtoToEntity(CategoriaDTO dto, Categoria entidade) {
		entidade.setNome(dto.getNome());
		entidade.setDescricao(dto.getDescricao());
		entidade.setImgUrl(dto.getImgUrl());
	}
}
