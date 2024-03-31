package com.uni.foodstock.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uni.foodstock.dto.ProdutoDTO;
import com.uni.foodstock.entidade.Produto;
import com.uni.foodstock.repositories.ProdutoRepositori;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepositori repositori;

	@Transactional(readOnly = true) /* Buscar por ID */
	public ProdutoDTO findById(Long id) {
		Produto produto = repositori.findById(id).get();
		return new ProdutoDTO(produto);

	}

	@Transactional(readOnly = true) /* Buscar todos */
	public Page<ProdutoDTO> findAll(Pageable pageable) {
		Page<Produto> result = repositori.findAll(pageable);
		return result.map(x -> new ProdutoDTO(x));

	}

	@Transactional /* Inserir novo produto */
	public ProdutoDTO insert(ProdutoDTO dto) {

		Produto entidade = new Produto();
		copyDtoToEntity(dto, entidade);
		entidade = repositori.save(entidade);
		return new ProdutoDTO(entidade);

	}

	@Transactional /* Inserir novo produto */
	public ProdutoDTO update(Long id, ProdutoDTO dto) {

		Produto entidade = repositori.getReferenceById(id);
		copyDtoToEntity(dto, entidade);
		entidade = repositori.save(entidade);
		return new ProdutoDTO(entidade);

	}
	
	@Transactional /* Deletor por ID */
	public void delete(Long id) {
		repositori.deleteById(id);
	}

	private void copyDtoToEntity(ProdutoDTO dto, Produto entidade) {
		entidade.setNome(dto.getNome());
		entidade.setMarca(dto.getMarca());
		entidade.setImgUrl(dto.getImgUrl());

	}

}
