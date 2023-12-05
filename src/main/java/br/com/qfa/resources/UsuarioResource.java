package br.com.qfa.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.qfa.resources.domain.user.User;
import br.com.qfa.services.UserService;

@RestController
@RequestMapping(value = "users")
@CrossOrigin("*")
public class UsuarioResource {

	@Autowired
	private UserService service;

	@GetMapping("/login")
	public ResponseEntity<User> find(@RequestParam(value="value") String login) {
		User obj = service.findByLogin(login);
		return ResponseEntity.ok().body(obj);
	}

}
