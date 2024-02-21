package br.com.qfa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.qfa.repositories.ItemPedidoRepository;
import br.com.qfa.resources.domain.ItemPedido;

@Service
public class ItemPedidoService {

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public List<ItemPedido> findByIdProduto(Integer id_produto) {
		return itemPedidoRepository.findByIdProduto(id_produto);
	}
	
	public List<ItemPedido> findByIdPedido(Integer id_categoria) {
		return itemPedidoRepository.findByIdPedido(id_categoria);
	}

	public List<ItemPedido> findAll() {
		return itemPedidoRepository.findAll();
	}

}
