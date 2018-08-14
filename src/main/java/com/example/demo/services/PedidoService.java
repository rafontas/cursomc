package com.example.demo.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.ItemPedido;
import com.example.demo.domain.PagamentoComBoleto;
import com.example.demo.domain.Pedido;
import com.example.demo.domain.enuns.EstadoPagamento;
import com.example.demo.repositories.ItemPedidoRepository;
import com.example.demo.repositories.PagamentoRepository;
import com.example.demo.repositories.PedidoRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repositorio;
	
	@Autowired
	private BoletoService servicoBoleto;

	@Autowired
	private PagamentoRepository repositorioPagamento;
	
	@Autowired
	private ProdutoService servicoProduto;
	
	@Autowired
	private ItemPedidoRepository repositorioItemPedido;
	
	public Pedido buscar(Integer id) throws ObjectNotFoundException {
		Optional<Pedido> obj = repositorio.findById(id);
		
		return obj.orElseThrow(() -> 
			new ObjectNotFoundException("Não foi encontrada objeto de id "+ id + ". Tipo: " + Pedido.class.getName()));
	}
	
	public void add(Pedido C) {
		repositorio.save(C);
	}
	
	public Pedido inserir(Pedido obj) throws ObjectNotFoundException {
		obj.setId(null);
		obj.setInstante(new Date());
		
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pgto = (PagamentoComBoleto) obj.getPagamento();
			servicoBoleto.PreencherPagamentoComBoleto(pgto, obj.getInstante());
		}
		
		obj = repositorio.save(obj);
		repositorioPagamento.save(obj.getPagamento());
		
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(servicoProduto.buscar(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		
		repositorioItemPedido.saveAll(obj.getItens());
		return obj;
	}
}
