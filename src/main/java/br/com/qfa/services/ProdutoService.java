package br.com.qfa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.qfa.repositories.CategoriaRepository;
import br.com.qfa.repositories.ProdutoRepository;
import br.com.qfa.resources.domain.Categoria;
import br.com.qfa.resources.domain.Produto;
import br.com.qfa.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Produto find(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}

	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);	
	}
	
	public Produto findByNome(String nome) {
		Produto obj = repo.findByNome(nome);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Produto não encontrado! Nome: " + nome);
		}
		return obj;
	}

	public List<Produto> findAll() {
		return repo.findAll();
	}

}
