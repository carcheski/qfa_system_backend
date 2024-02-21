package br.com.qfa.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.qfa.resources.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	
	@Transactional(readOnly = true)
	@Query("SELECT obj FROM Pedido obj INNER JOIN cliente cli WHERE cli.tipo = :tipoCliente")
	public List<Pedido> findByTipoCliente(@Param("tipoCliente") Integer tipo);
	
	@Transactional(readOnly = true)
	@Query("SELECT obj FROM Pedido obj INNER JOIN cliente cli WHERE cli.id = :idCliente ORDER BY obj.id desc LIMIT 1")
	public Pedido findByIdCliente(@Param("idCliente") Integer idCliente);

	@Transactional(readOnly = true)
	@Query("SELECT obj FROM Pedido obj WHERE obj.instante BETWEEN :dtInicial AND :dtFinal GROUP BY obj.instante ORDER BY obj.instante asc")
	public List<Pedido> findAllByDatas(Date dtInicial, Date dtFinal);

}
