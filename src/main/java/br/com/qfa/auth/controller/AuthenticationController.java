package br.com.qfa.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.qfa.dto.EmailDTO;
import br.com.qfa.repositories.UserRepository;
import br.com.qfa.resources.domain.user.AuthenticationDTO;
import br.com.qfa.resources.domain.user.LoginResponseDTO;
import br.com.qfa.resources.domain.user.RegisterDTO;
import br.com.qfa.resources.domain.user.User;
import br.com.qfa.services.AuthService;
import br.com.qfa.services.TokenService;
import br.com.qfa.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
@CrossOrigin("*")
public class AuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserRepository repository;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private AuthService service;

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
		var auth = this.authenticationManager.authenticate(usernamePassword);

		var token = tokenService.generateToken((User) auth.getPrincipal());

		return ResponseEntity.ok(new LoginResponseDTO(token));
	}

	@PostMapping("/register")
	public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
		if (this.repository.findByLogin(data.login()) != null)
			return ResponseEntity.badRequest().build();

		String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
		User newUser = new User(data.login(), encryptedPassword, data.role());

		this.repository.save(newUser);

		return ResponseEntity.ok().build();
	}

	@PostMapping("/refresh_token")
	public ResponseEntity refreshToken(HttpServletResponse response) {
		User user = UserService.authenticated();
		var token = tokenService.generateToken(user);
		return ResponseEntity.ok(new LoginResponseDTO(token));
	}

	@PostMapping("/forgot")
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDto) {
		service.sendNewPassword(objDto.getEmail());
		return ResponseEntity.noContent().build();
	}

}