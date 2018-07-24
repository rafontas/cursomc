package com.example.demo.services;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import java.util.List;
import java.util.Optional;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.demo.DTOS.ClienteDTO;
import com.example.demo.domain.Cliente;
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
	
	public Cliente update(Cliente obj) throws ObjectNotFoundException {
		Cliente newObj = this.buscar(obj.getId());
		atualizaDados(newObj, obj);
		return repositorio.save(obj);
	}
	
	public void delete(Integer id) throws ObjectNotFoundException {
		buscar(id);
		try{
			repositorio.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new ObjectNotFoundException("Não é possível excluir pois há entidades relacionadas."); 
		}
	}

	public List<Cliente> buscarTodos () {
		return repositorio.findAll();
	}

	public Page<Cliente> buscarPagina(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repositorio.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
	}
	
	private void atualizaDados(Cliente novo, Cliente antigo) {
		novo.setNome(antigo.getNome());
		novo.setEmail(antigo.getEmail());		
	}
	
}
