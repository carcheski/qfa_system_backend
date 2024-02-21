package br.com.qfa.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.qfa.repositories.ItemPedidoRepository;
import br.com.qfa.repositories.PagamentoRepository;
import br.com.qfa.repositories.PedidoRepository;
import br.com.qfa.resources.domain.ItemPedido;
import br.com.qfa.resources.domain.Pedido;
import br.com.qfa.resources.domain.enums.EstadoPagamento;
import br.com.qfa.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ClienteService clienteService;


	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		//obj.setInstante(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		if(obj.getPagamento().getEstado().getDescricao() != "FINALIZAR")
			obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		else
			obj.getPagamento().setEstado(EstadoPagamento.QUITADO);
		obj.getPagamento().setPedido(obj);
		/*
		 * if (obj.getPagamento() instanceof PagamentoComBoleto) { PagamentoComBoleto
		 * pagto = (PagamentoComBoleto) obj.getPagamento();
		 * boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante()); }
		 */
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		//emailService.sendOrderConfirmationHtmlEmail(obj);
		return obj;
	}
	
	public void update(Pedido obj) {
		//obj.setInstante(new Date());
		obj.getPagamento().setPedido(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		obj = repo.save(obj);
	}
	
	
	public List<Pedido> findByTipoCliente(Integer tipo){
		return repo.findByTipoCliente(tipo);
	}

	public Pedido findByIdCliente(Integer id) {
		return repo.findByIdCliente(id);
	}

	public List<Pedido> findAll() {
		return repo.findAll();
	}
	
	public List<Pedido> findAllByDatas(Date dtInicial , Date dtFinal) {
		return repo.findAllByDatas(dtInicial, dtFinal);
	}

}
