package br.com.qfa.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.qfa.resources.domain.ItemPedido;
import br.com.qfa.services.ItemPedidoService;

@RestController
@RequestMapping(value="/itempedido")
@CrossOrigin("*")
public class ItemPedidoResource {

	@Autowired
	private ItemPedidoService service;
	
	@GetMapping("/produto/{id_produto}")
	public ResponseEntity<List<ItemPedido>> findByIdProduto(@PathVariable Integer id_produto) {
		
		List<ItemPedido> obj = service.findByIdProduto(id_produto);
		return ResponseEntity.ok().body(obj);
		
	}
	
	@GetMapping("/pedido/{id_pedido}")
	public ResponseEntity<List<ItemPedido>> findByIdPedido(@PathVariable Integer id_pedido) {
		
		List<ItemPedido> obj = service.findByIdPedido(id_pedido);
		return ResponseEntity.ok().body(obj);
		
	}
	
	@GetMapping("/")
	public ResponseEntity<List<ItemPedido>> findAll() {

		List<ItemPedido> produtos = service.findAll();
		return ResponseEntity.ok().body(produtos);

	}
	
}
