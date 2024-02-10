package br.com.qfa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.qfa.repositories.EnderecoRepository;
import br.com.qfa.resources.domain.Endereco;
import br.com.qfa.services.exceptions.DataIntegrityException;
import br.com.qfa.services.exceptions.ObjectNotFoundException;

@Service
public class EnderecoService {
	
	@Autowired
	EnderecoRepository repo;
	
	public Endereco find(Integer id) {
		Optional<Endereco> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id));
	}

	public List<Endereco> findAll() {
		return repo.findAll();
	}

	public Endereco insert(Endereco obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}

	public Endereco update(Endereco obj) {
		return repo.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel exluir porque há pedidos relacionados!!!");
		}
		
	}

}
