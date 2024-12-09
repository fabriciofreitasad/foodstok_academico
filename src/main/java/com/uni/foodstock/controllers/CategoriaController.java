package com.uni.foodstock.controllers;

import java.io.IOException;
import java.net.URI;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.uni.foodstock.dto.CategoriaDTO;
import com.uni.foodstock.services.CategoryService;

@RestController
@RequestMapping(value = "/categoria")
@CrossOrigin("*")
public class CategoriaController {

	@Autowired
	private CategoryService service;

	@GetMapping(value = "/{id}") 										/* Buscar por ID */
	public ResponseEntity<CategoriaDTO> findById(@PathVariable Long id) {
		CategoriaDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping 														/* Buscar todos */
	public ResponseEntity<Page<CategoriaDTO>> findAll(Pageable pageable) {
		Page<CategoriaDTO> dto = service.findAll(pageable);
		return ResponseEntity.ok(dto);
	}

	@PostMapping
	public ResponseEntity<CategoriaDTO> insert(@RequestParam("file") MultipartFile file, @RequestParam("categoria") String categoriaJson) throws IOException {

			byte[] imagemBytes = file.getBytes();
			CategoriaDTO categoriaDTO = new ObjectMapper().readValue(categoriaJson, CategoriaDTO.class);

			categoriaDTO.setImagem(imagemBytes);

			CategoriaDTO savedCategoria = service.insert(categoriaDTO);

			return ResponseEntity.ok(savedCategoria);

	}

	@PutMapping(value = "/{id}") 										/* Atualizar cateria ID */
	public ResponseEntity<CategoriaDTO> update(@PathVariable Long id, @RequestParam("file") MultipartFile file, @RequestParam("categoria") String categoriaJson) throws IOException {
		byte[] imagemBytes = file.getBytes();
		CategoriaDTO categoriaDTO = new ObjectMapper().readValue(categoriaJson, CategoriaDTO.class);

		categoriaDTO.setImagem(imagemBytes);
		CategoriaDTO savedCategoria = service.update(id, categoriaDTO);
		return ResponseEntity.ok(savedCategoria);
	}

	@DeleteMapping(value = "/{id}") 									/* Deletar um categoria ID */
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}