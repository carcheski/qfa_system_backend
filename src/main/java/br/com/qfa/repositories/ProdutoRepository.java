package br.com.qfa.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.qfa.resources.domain.Categoria;
import br.com.qfa.resources.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	/**
	 * NESTE CASO PODEMOS DEIXAR SOMENTE A CONSULTA SEM A QUERY
	 * POREM COM A QUERY, O JPA IRÁ PRIORIZAR O QUE ESTÁ NA QUERY
	 */
	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE UPPER(obj.nome) LIKE UPPER(concat('%',:nome,'%')) AND cat IN :categorias")
	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias, Pageable pageRequest);
}
