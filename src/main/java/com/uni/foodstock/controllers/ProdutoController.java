package com.uni.foodstock.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.uni.foodstock.dto.ProdutoDTO;
import com.uni.foodstock.services.ProdutoService;

@RestController

@RequestMapping(value = "/produtos")
@CrossOrigin("*")
public class ProdutoController {

	@Autowired
	private ProdutoService service;

	@GetMapping(value = "/{id}") 										/* Buscar por ID */
	public ResponseEntity<ProdutoDTO> findById(@PathVariable Long id) {
		ProdutoDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}
	@GetMapping
	public ResponseEntity<Page<ProdutoDTO>> findAll(
			@RequestParam(value = "nome-produto", required = false) String nomeProduto,
			@RequestParam(value = "nome-categoria", required = false) String nomeCategoria,
			Pageable pageable) {

		Page<ProdutoDTO> dto = service.findAll(nomeProduto, nomeCategoria, pageable);
		return ResponseEntity.ok(dto);
	}


	@PostMapping 														/* Inserir novo produto */
	public ResponseEntity<ProdutoDTO> insert(@RequestBody ProdutoDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@PutMapping(value = "/{id}") 										/* Atualizar produto ID */
	public ResponseEntity<ProdutoDTO> update(@PathVariable Long id, @RequestBody ProdutoDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok(dto);
	}

	@DeleteMapping(value = "/{id}") 									/* Deletar um produto ID */
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}