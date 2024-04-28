package com.uni.foodstock.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.uni.foodstock.dto.EstoqueDTO;
import com.uni.foodstock.services.EstoqueService;

@RestController
@RequestMapping(value = "/estoques")
public class EstoqueController {
	
	@Autowired
	private EstoqueService service;

	@GetMapping(value = "/{id}") 										/* Buscar por ID */
	public ResponseEntity<EstoqueDTO> findById(@PathVariable Long id) {
		EstoqueDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping 														/* Buscar todos */
	public ResponseEntity<Page<EstoqueDTO>> findAll(Pageable pageable) {
		Page<EstoqueDTO> dto = service.findAll(pageable);
		return ResponseEntity.ok(dto);
	}

	@PostMapping 														/* Inserir novo Estoque */
	public ResponseEntity<EstoqueDTO> insert(@RequestBody EstoqueDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@PutMapping(value = "/{id}") 										/* Atualizar Estoque ID */
	public ResponseEntity<EstoqueDTO> update(@PathVariable Long id, @RequestBody EstoqueDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok(dto);
	}
	
	@DeleteMapping(value = "/{id}") 									/* Deletar um Estoque ID */
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
