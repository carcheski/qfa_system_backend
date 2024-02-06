package br.com.qfa.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.qfa.dto.CategoriaDTO;
import br.com.qfa.repositories.CategoriaRepository;
import br.com.qfa.repositories.ProdutoRepository;
import br.com.qfa.resources.domain.Categoria;
import br.com.qfa.resources.domain.Produto;
import br.com.qfa.services.exceptions.DataIntegrityException;
import br.com.qfa.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public void insert(Categoria obj) {
		obj.setId(null);
		repo.saveAll(Arrays.asList(obj));
	}
	
	public void update(Categoria obj) {
		this.salvarProdutoCategoria(obj);
		repo.saveAll(Arrays.asList(obj));
	}
	
	private void salvarProdutoCategoria(Categoria obj) {
		Produto prodNovo = new Produto();
		for (Produto produto : obj.getProdutos()) {
			prodNovo = produtoRepository.findByIdProduto(produto.getId());
			if(prodNovo != null && !(prodNovo.getCategorias().contains(obj))) {
				prodNovo.getCategorias().addAll(Arrays.asList(obj));
				produtoRepository.save(prodNovo);
			}else if(prodNovo == null) {
				prodNovo = produto;
				prodNovo.getCategorias().addAll(Arrays.asList(obj));
				produtoRepository.save(prodNovo);
			}
		}
		
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel exluir uma categoria que possui produtos!!!");
		}
	}
	
	public List<Categoria> findAll() {
		return repo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO categoriaDTO) {
		return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
	}
	
	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
	}

}
