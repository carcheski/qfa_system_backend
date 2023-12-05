package br.com.qfa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.qfa.resources.domain.user.User;

@Repository
public interface UsuarioRepository extends JpaRepository<User, String> {
    User findByLogin(String login);

}