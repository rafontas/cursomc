package com.example.demo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Categoria;
import com.example.demo.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService servico;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> Find(@PathVariable Integer id) {
		try {
			Categoria c = servico.buscar(id);
			return ResponseEntity.ok().body(c);
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().body(null);
		}
	} 

//	public ResponseEntity<?> Add(@PathVariable Integer id, @PathVariable String nome) {
//		try {
//			Categoria c = new Categoria(id, nome);
//			servico.add(c);
//			
//			return ResponseEntity.ok().body(c);
//		}
//		catch(Exception e) {
//			return ResponseEntity.badRequest().body(null);
//		}
//	} 
}
