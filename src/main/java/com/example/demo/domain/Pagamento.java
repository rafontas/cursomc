package com.example.demo.domain;

import java.io.Serializable;

import com.example.demo.domain.enuns.EstadoPagamento;

public class Pagamento implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
		super();
		Id = id;
		this.estado = estado;
		this.pedido = pedido;
	}

	private Integer Id;
	private EstadoPagamento estado;

	private Pedido pedido;
	
	public Pagamento() {}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public EstadoPagamento getEstado() {
		return estado;
	}

	public void setEstado(EstadoPagamento estado) {
		this.estado = estado;
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

