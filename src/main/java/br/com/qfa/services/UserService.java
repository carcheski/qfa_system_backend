package br.com.qfa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.qfa.repositories.UsuarioRepository;
import br.com.qfa.resources.domain.Cliente;
import br.com.qfa.resources.domain.user.User;
import br.com.qfa.resources.domain.user.UserRole;
import br.com.qfa.services.exceptions.AuthorizationException;
import br.com.qfa.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public static User authenticated() {
		try {
			return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}


	public User findByLogin(String login) {
		User user = UserService.authenticated();
		if (user == null || !user.hasRole(UserRole.ADMIN) && !login.equals(user.getLogin())) {
			throw new AuthorizationException("Acesso negado");
		}

		User obj = usuarioRepository.findByLogin(login);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Cliente.class.getName());
		}
		return obj;
	}

}
