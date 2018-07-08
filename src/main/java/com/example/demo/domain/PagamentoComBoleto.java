package com.example.demo.domain;


import java.util.Date;

import javax.persistence.Entity;

import com.example.demo.domain.enuns.EstadoPagamento;

@Entity
public class PagamentoComBoleto extends Pagamento {

	private static final long serialVersionUID = 1L;
	
	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date date, Date dataPagamento) {
		super(id, estado, pedido);
		this.dataVencimento = date;
		this.dataPagamento = dataPagamento;
	}

	private Date dataVencimento;
	private Date dataPagamento;	

	
	public PagamentoComBoleto() {}


	public Date getDataVencimento() {
		return dataVencimento;
	}


	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}


	public Date getDataPagamento() {
		return dataPagamento;
	}


	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
}
