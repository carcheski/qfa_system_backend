package br.com.qfa.services;


import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.qfa.repositories.UserRepository;
import br.com.qfa.resources.domain.user.AuthenticationDTO;
import br.com.qfa.resources.domain.user.LoginResponseDTO;
import br.com.qfa.resources.domain.user.RegisterDTO;
import br.com.qfa.resources.domain.user.User;
import jakarta.validation.Valid;

@Service
public class AuthorizationService implements UserDetailsService {

	@Autowired
	private ApplicationContext context;

	@Autowired
	private UserRepository repository;

	@Autowired
	private TokenService tokenService;

	private AuthenticationManager authenticationManager;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repository.findByLogin(username);
	}
	
	public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDTO data){
        authenticationManager = context.getBean(AuthenticationManager.class);

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }


    public ResponseEntity<Object> register (@RequestBody RegisterDTO registerDTO){
        if (this.repository.findByLogin(registerDTO.login()) != null ) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        
        User newUser = new User(registerDTO.login(), encryptedPassword, registerDTO.role());
        newUser.setCreatedAt(new Date(System.currentTimeMillis()));
        this.repository.save(newUser);
        return ResponseEntity.ok().build();
    }

}