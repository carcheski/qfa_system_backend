package br.com.qfa.dto;

import java.io.Serializable;

import br.com.qfa.resources.domain.Estado;

public class EstadoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private String uf;

	public EstadoDTO() {
	}

	public EstadoDTO(Estado obj) {
		id = obj.getId();
		nome = obj.getNome();
		setUf(obj.getUf());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
}	