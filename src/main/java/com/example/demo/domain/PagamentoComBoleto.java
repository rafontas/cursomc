package com.example.demo.domain;


import java.util.Date;

import javax.persistence.Entity;

import com.example.demo.domain.enuns.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class PagamentoComBoleto extends Pagamento {

	private static final long serialVersionUID = 1L;
	
	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date date, Date dataPagamento) {
		super(id, estado, pedido);
		this.dataVencimento = date;
		this.dataPagamento = dataPagamento;
	}

	@JsonFormat(pattern="dd/MM/yyy HH:mm")
	private Date dataVencimento;
	
	@JsonFormat(pattern="dd/MM/yyy HH:mm")
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
