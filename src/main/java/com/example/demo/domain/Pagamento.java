package com.example.demo.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.example.demo.domain.enuns.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonIgnore;


/*
 * Comentário Explicação:
 * Entity: Indica que é uma entidade;
 * Inheritance: Indica que classes irão herdar desta aqui;
 * 		- strategy=InheritanceType.JOINED: Tem duas maneiras de criar no banco uma herança.
 * 			1. Uma tabelona que é mais rápida, mas ocupa mais espaço.
 * 			2. Uma tabela pra cada entidade. Mais lento mas mais modularizado e ocupa menos espaço.
 * 	
 * 	Obs: Quando tem MUITAS colunas ou exija eficiência vá de 1. Se não, vá de 2.
 * 
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Pagamento implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public Pagamento(Integer id, EstadoPagamento estadoPagamento, Pedido pedido) {
		super();
		Id = id;
		this.estadoPagamento = estadoPagamento.getCod();
		this.pedido = pedido;
	}

	@Id // Indica que será o Id da tabela
	private Integer Id;
	private Integer estadoPagamento;

	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="pedido_id")
	@MapsId
	private Pedido pedido;
	
	public Pagamento() {}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public EstadoPagamento getEstado() {
		return EstadoPagamento.toEnum(this.estadoPagamento);
	}

	public void setEstado(EstadoPagamento estado) {
		this.estadoPagamento = estado.getCod();
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pagamento other = (Pagamento) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		return true;
	}
}

