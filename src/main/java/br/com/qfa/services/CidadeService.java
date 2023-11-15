package br.com.qfa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.qfa.repositories.CidadeRepository;
import br.com.qfa.resources.domain.Cidade;

@Service	
public class CidadeService {

	@Autowired
	CidadeRepository cidadeRepository;
	
	public List<Cidade> findByEstado(Integer estadoId){
		return cidadeRepository.findCidades(estadoId);
	}
}
