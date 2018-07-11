package com.examples.demo.excecoes;

public class DadoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DadoNaoEncontradoException (String msg) {
		super(msg);
	}

	public DadoNaoEncontradoException (String msg, Throwable cause) {
		super(msg, cause);
	}	
}
