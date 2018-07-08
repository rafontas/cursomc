package com.example.demo.domain.enuns;

public enum EstadoPagamento {

	PENDENTE (1, "Pendente"),
	QUITADO (2, "Quitado"),
    CANCELADO (3, "Cancelado");
	
	private int cod;
	private String descricao;
	
	private EstadoPagamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static EstadoPagamento toEnum(Integer codigo) 
	{
		if(codigo == null) return null;
		
		for(EstadoPagamento x : EstadoPagamento.values())
			if(codigo.equals(x.getCod())) return x;
		
		throw new IllegalArgumentException("O enumerado de código " + codigo + " não existe.");
	}
}

