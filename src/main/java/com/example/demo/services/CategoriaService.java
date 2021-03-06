package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.demo.DTOS.CategoriaDTO;
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

	public Categoria update(Categoria obj) throws ObjectNotFoundException {
		Categoria newObj = this.buscar(obj.getId());
		this.atualizaDados(newObj, obj);
		return repositorio.save(obj);
	}
	
	public void delete(Integer id) throws ObjectNotFoundException {
		buscar(id);
		try{
			repositorio.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new ObjectNotFoundException("Não é possível excluir uma categoria que possui produtos."); 
		}
	}

	public List<Categoria> buscarTodos () {
		return repositorio.findAll();
	}

	public Page<Categoria> buscarPagina(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repositorio.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO objDTO) {
		return new Categoria(objDTO.getId(), objDTO.getNome());
	}
	
	private void atualizaDados(Categoria novo, Categoria antigo) {
		novo.setNome(antigo.getNome());
	}	
}
