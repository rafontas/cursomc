package com.example.demo.domain.enuns;

public enum TipoCliente {

	PESSOAFISICA (1, "Pessoa Física"),
	PESSOAJURIDICA (2, "Pessoa Jurísica");

	private int cod;
	private String descricao;
	
	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoCliente toEnum(Integer codigo) 
	{
		if(codigo == null) return null;
		
		for(TipoCliente x : TipoCliente.values())
			if(codigo.equals(x.getCod())) return x;
		
		throw new IllegalArgumentException("O enumerado de código " + codigo + " não existe.");
	}
}

