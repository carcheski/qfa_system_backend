package br.com.qfa.dto;

import java.io.Serializable;
import java.util.Date;

public class VendasDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer numPedido;
	private Double vlrCompra;
	private Double totVlrCompra;
	private Double vlrVenda;
	private Double totVlrVenda;
	private Integer qtdVendido;
	private String produto;
	private Date dataInicial;
	private Date dataFinal;
	private Date dataVenda;
	private Double percLucro;

	public VendasDTO() {
		super();
	}

	public Integer getNumPedido() {
		return numPedido;
	}

	public void setNumPedido(Integer numPedido) {
		this.numPedido = numPedido;
	}

	public Double getVlrCompra() {
		return vlrCompra;
	}

	public void setVlrCompra(Double vlrCompra) {
		this.vlrCompra = vlrCompra;
	}

	public Double getTotVlrCompra() {
		return totVlrCompra;
	}

	public void setTotVlrCompra(Double totVlrCompra) {
		this.totVlrCompra = totVlrCompra;
	}

	public Double getVlrVenda() {
		return vlrVenda;
	}

	public void setVlrVenda(Double vlrVenda) {
		this.vlrVenda = vlrVenda;
	}

	public Double getTotVlrVenda() {
		return totVlrVenda;
	}

	public void setTotVlrVenda(Double totVlrVenda) {
		this.totVlrVenda = totVlrVenda;
	}

	public Integer getQtdVendido() {
		return qtdVendido;
	}

	public void setQtdVendido(Integer qtdVendido) {
		this.qtdVendido = qtdVendido;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public Double getPercLucro() {
		return percLucro;
	}

	public void setPercLucro(Double percLucro) {
		this.percLucro = percLucro;
	}

}
