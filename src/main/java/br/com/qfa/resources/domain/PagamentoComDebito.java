package br.com.qfa.resources.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;

import br.com.qfa.resources.domain.enums.EstadoPagamento;
import jakarta.persistence.Entity;

@Entity
@JsonTypeName("pagamentoComDebito")
public class PagamentoComDebito extends Pagamento{
	
	private static final long serialVersionUID = 1L;
	
	private Integer numeroDeParcelas;
	
	public PagamentoComDebito() {
		
	}

	public PagamentoComDebito(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
		super(id, estado, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
	}

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}
	
	
}
