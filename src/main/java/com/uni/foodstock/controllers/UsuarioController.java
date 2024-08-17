
package com.uni.foodstock.controllers;

import com.uni.foodstock.dto.LoginRequestDTO;
import com.uni.foodstock.dto.ResgisterRequestDTO;
import com.uni.foodstock.dto.ResponseDTO;
import com.uni.foodstock.entidade.Usuario;
import com.uni.foodstock.repositories.UsuarioRepositori;
import com.uni.foodstock.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/auth")
public class UsuarioController {

	@Autowired
	private  UsuarioRepositori usuarioRepository;
	@Autowired
	private  PasswordEncoder passwordEncoder;
	@Autowired
	private  TokenService tokenService;

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody LoginRequestDTO login){
		Usuario user = this.usuarioRepository.findByEmail(login.email()).orElseThrow(()-> new RuntimeException("User not Found"));
		if(passwordEncoder.matches(login.password(), user.getSenha())){
			String token = this.tokenService.generateToken(user);
			return ResponseEntity.ok(new ResponseDTO(user.getNome(),token));
		}
		return ResponseEntity.badRequest().build();
	}
	@PostMapping("/register")
	public ResponseEntity register(@RequestBody ResgisterRequestDTO register){
		Optional<Usuario> user = this.usuarioRepository.findByEmail(register.email());
		if(user.isEmpty()){
			Usuario newUser = new Usuario();
			newUser.setSenha(passwordEncoder.encode(register.password()));
			newUser.setEmail(register.email());
			newUser.setNome(register.name());
			this.usuarioRepository.save(newUser);

			String token = this.tokenService.generateToken(newUser);
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(newUser.getNome(),token));
		}

		return ResponseEntity.badRequest().build();
	}


}
