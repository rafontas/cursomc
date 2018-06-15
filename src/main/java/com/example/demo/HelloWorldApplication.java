package com.example.demo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.domain.Categoria;
import com.example.demo.domain.Cidade;
import com.example.demo.domain.Estado;
import com.example.demo.domain.Produto;
import com.example.demo.repositories.CategoriaRepository;
import com.example.demo.repositories.CidadeRepository;
import com.example.demo.repositories.EstadoRepository;
import com.example.demo.repositories.ProdutoRepository;

@SpringBootApplication
public class HelloWorldApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository RepositorioCategoria;

	@Autowired
	private ProdutoRepository RepositorioProduto;
	
	@Autowired
	private EstadoRepository RepositorioEstado;
	
	@Autowired
	private CidadeRepository RepositorioCidade;
	
	public static void main(String[] args) {
		SpringApplication.run(HelloWorldApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Salva os produtos básicos
		Categoria c1 = new Categoria(null, "Informática");
		Categoria c2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		// Salva as associações
		c1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		c2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(c1));
		p2.getCategorias().addAll(Arrays.asList(c1, c2));
		p3.getCategorias().addAll(Arrays.asList(c1));
		
		Estado e1 = new Estado(null, "Minas Gerais");
		Estado e2 = new Estado(null, "São Paulo");
		
		Cidade cdd1 = new Cidade(null, "Belo Horizonte", e1);
		Cidade cdd2 = new Cidade(null, "Sâo Paulo", e2);
		Cidade cdd3 = new Cidade(null, "Campinas", e2);
		
		e1.setCidades(Arrays.asList(cdd1));
		e2.setCidades(Arrays.asList(cdd2, cdd3));
		
//		RepositorioEstado.saveAll(Arrays.asList(e1, e2));
//		RepositorioCidade.saveAll(Arrays.asList(cdd1, cdd2, cdd3));
		
		// Salva os produtos
		// RepositorioCategoria.saveAll(Arrays.asList(c1, c2));
		// RepositorioProduto.saveAll(Arrays.asList(p1, p2, p3));
	}
}
