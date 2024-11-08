
package com.uni.foodstock.security;



import com.uni.foodstock.controllers.handlers.ControllerExceptionHandler;
import com.uni.foodstock.entities.Usuario;
import com.uni.foodstock.repositories.UsuarioRepository;
import com.uni.foodstock.security.dto.*;
import com.uni.foodstock.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/auth")
//@Profile("secure")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private  TokenService tokenService;
	@Autowired private JavaMailSender mail;

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody LoginRequestDTO login){
		Usuario user = this.usuarioRepository.findByEmail(login.email()).orElseThrow(()-> new RuntimeException("Usuario não existe!"));

		if(passwordEncoder.matches(login.password(), user.getSenha())){
			String token = this.tokenService.generateToken(user);
			return ResponseEntity.ok(new ResponseDTO(user.getNome(),token));
		}
		return ResponseEntity.badRequest().body("Usuário ou senha Incorretas!");
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
	@PostMapping("/change-password")
	public ResponseEntity<Void> alterarSenha(@RequestParam Long id, @RequestBody AlterarSenhaRequestDTO request) {
		Usuario usuario = this.usuarioRepository.findById(id) .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado para o e-mail: " ));
		usuario.setSenha(passwordEncoder.encode(request.novaSenha()));
		usuarioRepository.save(usuario);

		return ResponseEntity.ok(null);
	}
	@PostMapping("/recover")
	public ResponseEntity<Void> recover(@RequestBody RecoverRequestDTO email) {
		System.out.println(email);
		Usuario user = this.usuarioRepository.findByEmail(email.email())
				.orElseThrow(() -> new ResourceNotFoundException("Usuário com email " + email.email() + " não encontrado"));

		SimpleMailMessage simple = new SimpleMailMessage();
		simple.setFrom("recebimento.pix.declientes@gmail.com");
		simple.setTo(email.email());
		simple.setSubject("Recuperação de senha FoodStock!");
		simple.setText("Clique no link para redefinir sua senha: " + generateRecoveryLink(user));
		mail.send(simple);
		return ResponseEntity.ok(null);
	}
	public String generateRecoveryLink(Usuario usuario) {
		String emailPrefix = usuario.getEmail().split("@")[0];
		return "http://localhost:4200/alterar-senha?id=" + usuario.getId();
	}




}
