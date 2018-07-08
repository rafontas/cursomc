package com.example.demo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Pedido;
import com.example.demo.services.PedidoService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService servico;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> Find(@PathVariable Integer id) throws ObjectNotFoundException {
		Pedido c = servico.buscar(id);
		return ResponseEntity.ok().body(c);
	}  
}
