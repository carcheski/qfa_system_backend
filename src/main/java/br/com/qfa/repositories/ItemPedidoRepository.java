package br.com.qfa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.qfa.resources.domain.ItemPedido;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

	@Transactional(readOnly = true)
	@Query("SELECT item FROM ItemPedido item WHERE item.id.produto.id = :id_produto")
	List<ItemPedido> findByIdProduto(@Param("id_produto") Integer id_produto);
	
	@Transactional(readOnly = true)
	@Query("SELECT item FROM ItemPedido item WHERE item.id.pedido.id = :id_pedido")
	List<ItemPedido> findByIdPedido(@Param("id_pedido") Integer id_pedido);

}
