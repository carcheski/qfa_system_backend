package br.com.qfa.resources.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;

import br.com.qfa.resources.domain.enums.EstadoPagamento;
import jakarta.persistence.Entity;

@Entity
@JsonTypeName("pagamentoNoDinheiro")
public class PagamentoNoDinheiro extends Pagamento{
	
	private static final long serialVersionUID = 1L;
	
	public PagamentoNoDinheiro() {
		
	}

	public PagamentoNoDinheiro(Integer id, EstadoPagamento estado, Pedido pedido) {
		super(id, estado, pedido);
	}

	
}
