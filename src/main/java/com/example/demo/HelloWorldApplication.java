package com.example.demo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.domain.Categoria;
import com.example.demo.repositories.CategoriaRepository;

@SpringBootApplication
public class HelloWorldApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository Repositorio;
	
	public static void main(String[] args) {
		SpringApplication.run(HelloWorldApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria c1 = new Categoria(null, "Categoria 2");
		Categoria c2 = new Categoria(null, "Categoria 3");
		

		Repositorio.saveAll(Arrays.asList(c1, c2));
	}
}
