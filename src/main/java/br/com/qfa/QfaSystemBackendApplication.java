package br.com.qfa;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.qfa.repositories.CategoriaRepository;
import br.com.qfa.repositories.CidadeRepository;
import br.com.qfa.repositories.ClienteRepository;
import br.com.qfa.repositories.EnderecoRepository;
import br.com.qfa.repositories.EstadoRepository;
import br.com.qfa.repositories.ItemPedidoRepository;
import br.com.qfa.repositories.PagamentoRepository;
import br.com.qfa.repositories.PedidoRepository;
import br.com.qfa.repositories.ProdutoRepository;
import br.com.qfa.resources.domain.Categoria;
import br.com.qfa.resources.domain.Cidade;
import br.com.qfa.resources.domain.Cliente;
import br.com.qfa.resources.domain.Endereco;
import br.com.qfa.resources.domain.Estado;
import br.com.qfa.resources.domain.ItemPedido;
import br.com.qfa.resources.domain.Pagamento;
import br.com.qfa.resources.domain.PagamentoComBoleto;
import br.com.qfa.resources.domain.PagamentoComCartao;
import br.com.qfa.resources.domain.Pedido;
import br.com.qfa.resources.domain.Produto;
import br.com.qfa.resources.domain.enums.EstadoPagamento;
import br.com.qfa.resources.domain.enums.TipoCliente;

@SpringBootApplication
public class QfaSystemBackendApplication implements CommandLineRunner{
	
	@Autowired
	CategoriaRepository categoriaRespository;
	
	@Autowired
	ProdutoRepository produtoRespository;
	
	@Autowired
	EstadoRepository estadoRespository;
	
	@Autowired
	CidadeRepository cidadeRespository;
	
	@Autowired
	ClienteRepository clienteRespository;
	
	@Autowired
	EnderecoRepository enderecoRespository;
	
	@Autowired
	PedidoRepository pedidoRespository;
	
	@Autowired
	PagamentoRepository pagamentoRespository;
	
	@Autowired
	ItemPedidoRepository itemPedidoRespository;

	public static void main(String[] args) {
		SpringApplication.run(QfaSystemBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRespository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		
		produtoRespository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRespository.saveAll(Arrays.asList(est1, est2));
		
		cidadeRespository.saveAll(Arrays.asList(c1, c2, c3));
		
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "382220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Av Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRespository.saveAll(Arrays.asList(cli1));
		
		enderecoRespository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm"); 
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRespository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRespository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRespository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
	}
}
