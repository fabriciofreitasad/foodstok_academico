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

	@PutMapping(value = "/{id}")
	public ResponseEntity<CategoriaDTO> update(@PathVariable Long id,
											   @RequestParam(value = "file" ,required = false) MultipartFile file,
											   @RequestParam("categoria") String categoriaJson) throws IOException {

		// Converte o JSON recebido no parâmetro "categoria" para um DTO
		CategoriaDTO categoriaDTO = new ObjectMapper().readValue(categoriaJson, CategoriaDTO.class);

		// Caso um arquivo de imagem seja enviado, converte em byte[] e define no DTO
		if (file != null && !file.isEmpty()) {
			byte[] imagemBytes = file.getBytes();
			categoriaDTO.setImagem(imagemBytes);
		} else {
			// Caso não haja imagem enviada, vamos buscar a imagem existente
			CategoriaDTO categoriaExistente = service.findById(id); // Aqui você pode obter a categoria original pelo ID
			if (categoriaExistente != null) {
				categoriaDTO.setImagem(categoriaExistente.getImagem()); // Mantém a imagem existente
			}
		}

		// Atualiza a categoria com o DTO ajustado
		CategoriaDTO savedCategoria = service.update(id, categoriaDTO);

		return ResponseEntity.ok(savedCategoria);
	}


	@DeleteMapping(value = "/{id}") 									/* Deletar um categoria ID */
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}