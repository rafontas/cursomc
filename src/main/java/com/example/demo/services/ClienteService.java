package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DTOS.ClienteDTO;
import com.example.demo.DTOS.ClienteNewDTO;
import com.example.demo.domain.Cidade;
import com.example.demo.domain.Cliente;
import com.example.demo.domain.Endereco;
import com.example.demo.domain.enuns.TipoCliente;
import com.example.demo.repositories.ClienteRepository;
import com.example.demo.repositories.EnderecoRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repositorio;

	@Autowired
	private EnderecoRepository repositorioEndereco;	
	
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
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repositorio.save(obj);
		
		// Salva os endereços
		repositorioEndereco.saveAll(obj.getEnderecos());
		
		return obj;
	}

	public Cliente fromDTO(ClienteNewDTO objDTO) {
		Cliente cli =  new Cliente(null, objDTO.getNome(), objDTO.getEmail(),objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipoCliente()));
		Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
		Endereco end = new Endereco(null,objDTO.getLogradouro(),objDTO.getNumero(),objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cid, cli);		
		
		// Faz a relação dos clientes com endereços e telefones;
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDTO.getTelefone1());
		
		if(objDTO.getTelefone2() != null) { 
			cli.getTelefones().add(objDTO.getTelefone2()); 
		}
		if(objDTO.getTelefone3() != null) { 
			cli.getTelefones().add(objDTO.getTelefone3()); 
		}
		
		return cli;
	}
	
}
