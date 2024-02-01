package br.com.qfa.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.qfa.dto.ProdutoDTO;
import br.com.qfa.resources.domain.Produto;
import br.com.qfa.resources.utils.URL;
import br.com.qfa.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
@CrossOrigin("*")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;

	@GetMapping("/{id}")
	public ResponseEntity<Produto> find(@PathVariable Integer id) {
		Produto obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping("/nome")
	public ResponseEntity<Produto> find(@RequestParam(value="value") String nome) {
		Produto obj = service.findByNome(nome);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value="nome", defaultValue="") String nome, 
			@RequestParam(value="categorias", defaultValue="") String categorias, 
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Produto> list = service.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}
	
	
	@GetMapping("/")
	public ResponseEntity<List<ProdutoDTO>> findAll() {

		List<Produto> produtos = service.findAll();
		List<ProdutoDTO> listDTO = produtos.stream().map(obj -> new ProdutoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);

	}

}
