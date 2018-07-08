package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Cliente;
import com.example.demo.repositories.ClienteRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repositorio;
	
	public Cliente buscar(Integer id) throws ObjectNotFoundException {
		Optional<Cliente> obj = repositorio.findById(id);
		
		return obj.orElseThrow(() -> 
			new ObjectNotFoundException("Não foi encontrada objeto de id "+ id + ". Tipo: " + Cliente.class.getName()));
	}
	
	public void add(Cliente C) {
		repositorio.save(C);
	}
}
