package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>
{		
	/*
	 * Busca e-mail;
	 * 
	 * Padrão de nome por do Spring:
	 * 		Se o método retornar o tipo do meu repository Cliente no caso, então eu posso utilizar
	 * 		o método: findBy + "Nome do campo da classe", ele já implementa automaticamente.
	 * 
	 */
	@Transactional(readOnly=true)
	public Cliente findByEmail(String email);
	
}
