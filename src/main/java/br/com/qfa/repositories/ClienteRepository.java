package br.com.qfa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.qfa.resources.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	@Transactional(readOnly=true)
	Cliente findByEmail(String email);

	@Transactional(readOnly = true)
	@Query("SELECT obj FROM Cliente obj INNER JOIN obj.enderecos ender WHERE obj.id = :id ORDER BY obj.nome")
	Cliente findByIdCliente(Integer id);
	
	@Transactional(readOnly = true)
	@Query("SELECT obj FROM Cliente obj LEFT JOIN obj.enderecos ender WHERE obj.tipo = :tipo ORDER BY obj.nome")
	List<Cliente> findByTipo(Integer tipo);
}
