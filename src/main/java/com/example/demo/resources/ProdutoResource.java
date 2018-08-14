package com.example.demo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTOS.ProdutoDTO;
import com.example.demo.domain.Produto;
import com.example.demo.resources.uteis.URL;
import com.example.demo.services.ProdutoService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService servico;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Produto> Find(@PathVariable Integer id) throws ObjectNotFoundException {
		Produto c = servico.buscar(id);
		return ResponseEntity.ok().body(c);
	}  
	
	@RequestMapping(method=RequestMethod.GET)	
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value="categorias", defaultValue="") String categorias,
			@RequestParam(value="nome", defaultValue="") String nome,
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) throws ObjectNotFoundException {
		
		List<Integer> ids = URL.DecodeIntList(categorias);
		String nomeDecoded = URL.DecodeParam(nome);
		
		Page<Produto> list = servico.buscar(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDTO = list.map(obj -> new ProdutoDTO(obj));
		
		return ResponseEntity.ok().body(listDTO);
	}
}
