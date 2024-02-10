package br.com.qfa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.qfa.repositories.EstadoRepository;
import br.com.qfa.resources.domain.Estado;
import br.com.qfa.services.exceptions.ObjectNotFoundException;

@Service
public class EstadoService {

	@Autowired
	EstadoRepository estadoRepository;
	
	public List<Estado> findAll(){
		return estadoRepository.findAllByOrderByNome();
	}

	public Estado find(Integer id) {
		Optional<Estado> obj = estadoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Estado.class.getName()));
	}
}
