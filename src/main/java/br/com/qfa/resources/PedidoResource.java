package br.com.qfa.resources;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.qfa.resources.domain.Pedido;
import br.com.qfa.services.PedidoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/pedidos")
@CrossOrigin("*")
public class PedidoResource {

	@Autowired
	private PedidoService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> find(@PathVariable Integer id) {
		
		Pedido obj = service.find(id);
		return ResponseEntity.ok().body(obj);
		
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody Pedido obj, @PathVariable Integer id) {
		service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/cliente/tipo/{tipo}")
	public ResponseEntity<List<Pedido>> findByTipoCliente(@PathVariable Integer tipo) {
		List<Pedido> obj = service.findByTipoCliente(tipo);
		return ResponseEntity.ok().body(obj);
		
	}
	
	@GetMapping("/cliente/{id}")
	public ResponseEntity<Pedido> findByIdCliente(@PathVariable Integer id) {
		Pedido obj = service.findByIdCliente(id);
		return ResponseEntity.ok().body(obj);
		
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Pedido>> findAll() {
		List<Pedido> pedidos = service.findAll();
		return ResponseEntity.ok().body(pedidos);

	}
	
	@GetMapping("/{dtInicial}/e/{dtFinal}")
	public ResponseEntity<List<Pedido>> findAllByDatas(@PathVariable String dtInicial, @PathVariable String dtFinal) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Date dtIni;
			dtIni = format.parse(dtInicial);
			Date dtFim = format.parse(dtFinal);
			List<Pedido> pedidos = service.findAllByDatas(dtIni, dtFim);
			return ResponseEntity.ok().body(pedidos);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}
