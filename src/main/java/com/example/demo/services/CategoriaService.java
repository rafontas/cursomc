package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Categoria;
import com.example.demo.repositories.CategoriaRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repositorio;
	
	public Categoria buscar(Integer id) throws ObjectNotFoundException {
		Optional<Categoria> obj = repositorio.findById(id);
		
		return obj.orElseThrow(() -> 
			new ObjectNotFoundException("Não foi encontrada objeto de id "+ id + ". Tipo: " + Categoria.class.getName()));
	}
	
	public void add(Categoria C) {
		repositorio.save(C);
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repositorio.save(obj);
	}
}
