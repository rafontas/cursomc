package com.example.demo;

import java.io.Serializable;

import com.example.demo.domain.Categoria;

/*
 * Define quais o que será mostrado para a visão.
 * 
 */
public class CategoriaDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	
	public CategoriaDTO() {}

	public CategoriaDTO(Categoria Cat) {
		id = Cat.getId();
		nome = Cat.getNome();
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
	
}
