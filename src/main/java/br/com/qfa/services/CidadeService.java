package br.com.qfa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.qfa.repositories.CidadeRepository;
import br.com.qfa.resources.domain.Cidade;
import br.com.qfa.services.exceptions.ObjectNotFoundException;

@Service	
public class CidadeService {

	@Autowired
	CidadeRepository cidadeRepository;
	
	public List<Cidade> findByEstado(Integer estadoId){
		return cidadeRepository.findCidades(estadoId);
	}
	
	public Cidade find(Integer id) {
		Optional<Cidade> obj = cidadeRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cidade.class.getName()));
	}
}
