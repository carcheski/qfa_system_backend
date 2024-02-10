package br.com.qfa.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.qfa.resources.domain.Cidade;
import br.com.qfa.services.CidadeService;

@RestController
@RequestMapping(value="/cidades")
@CrossOrigin("*")
public class CidadeResource {
	
	
	@Autowired
	private CidadeService cidadeService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Cidade> find(@PathVariable Integer id) {
		Cidade obj = cidadeService.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
}	