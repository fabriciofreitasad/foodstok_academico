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

import com.uni.foodstock.dto.UsuarioDTO;
import com.uni.foodstock.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioService service;

	@GetMapping(value = "/{id}") 										/* Buscar por ID */
	public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
		UsuarioDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping 														/* Buscar todos */
	public ResponseEntity<Page<UsuarioDTO>> findAll(Pageable pageable) {
		Page<UsuarioDTO> dto = service.findAll(pageable);
		return ResponseEntity.ok(dto);
	}

	@PostMapping 														/* Inserir novo usuario */
	public ResponseEntity<UsuarioDTO> insert(@RequestBody UsuarioDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@PutMapping(value = "/{id}") 										/* Atualizar usuario ID */
	public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody UsuarioDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok(dto);
	}
	
	@DeleteMapping(value = "/{id}") 									/* Deletar um usuario ID */
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
