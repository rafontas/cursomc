package com.example.demo.DTOS;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.example.demo.domain.Categoria;

/*
 * Define quais o que será mostrado para a visão.
 * 
 */
public class CategoriaDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório.")
	@Length(min=5, max=80, message="Tamanho deve ser entre 5 e 80 caracteres.")
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
