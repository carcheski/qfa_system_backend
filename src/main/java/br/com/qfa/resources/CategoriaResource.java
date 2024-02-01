package br.com.qfa.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.qfa.dto.CategoriaDTO;
import br.com.qfa.resources.domain.Categoria;
import br.com.qfa.services.CategoriaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/categorias")
@CrossOrigin(origins = "*")
public class CategoriaResource {

	private static String caminhoImagens = "/Users/PC/Documents/imagens/";

	@Autowired
	private CategoriaService service;

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {

		Categoria obj = service.find(id);
		return ResponseEntity.ok().body(obj);

	}

	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDTO) {
		
		Categoria obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDTO, @PathVariable Integer id) {
		Categoria obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {

		service.delete(id);
		return ResponseEntity.noContent().build();

	}

	@GetMapping
	@CrossOrigin(origins = "*")
	public ResponseEntity<List<CategoriaDTO>> findAll() {

		List<Categoria> categorias = service.findAll();
		List<CategoriaDTO> listDTO = categorias.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);

	}

	@GetMapping("/page")
	public ResponseEntity<Page<CategoriaDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<Categoria> categorias = service.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoriaDTO> listDTO = categorias.map(obj -> new CategoriaDTO(obj));
		return ResponseEntity.ok().body(listDTO);

	}
}
