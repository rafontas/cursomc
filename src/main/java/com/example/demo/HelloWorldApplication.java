package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.domain.Categoria;
import com.example.demo.domain.Cidade;
import com.example.demo.domain.Cliente;
import com.example.demo.domain.Endereco;
import com.example.demo.domain.Estado;
import com.example.demo.domain.ItemPedido;
import com.example.demo.domain.Pagamento;
import com.example.demo.domain.PagamentoComBoleto;
import com.example.demo.domain.PagamentoComCartao;
import com.example.demo.domain.Pedido;
import com.example.demo.domain.Produto;
import com.example.demo.domain.enuns.EstadoPagamento;
import com.example.demo.domain.enuns.TipoCliente;
import com.example.demo.repositories.CategoriaRepository;
import com.example.demo.repositories.CidadeRepository;
import com.example.demo.repositories.ClienteRepository;
import com.example.demo.repositories.EnderecoRepository;
import com.example.demo.repositories.EstadoRepository;
import com.example.demo.repositories.ItemPedidoRepository;
import com.example.demo.repositories.PagamentoRepository;
import com.example.demo.repositories.PedidoRepository;
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

	@Autowired
	private EnderecoRepository RepositorioEndereco;
	
	@Autowired
	private ClienteRepository RepositorioCliente;
	
	@Autowired
	private PedidoRepository RepositorioPedido;

	@Autowired
	private PagamentoRepository RepositorioPagamento;

	@Autowired
	private ItemPedidoRepository RepositorioItemPedido;	
	
	public static void main(String[] args) {
		SpringApplication.run(HelloWorldApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Salva os produtos básicos
		Categoria c1 = new Categoria(4, "Informática");
		Categoria c2 = new Categoria(5, "Escritório");
		
		Produto p1 = new Produto(1, "Computador", 2000.00);
		Produto p2 = new Produto(2, "Impressora", 800.00);
		Produto p3 = new Produto(3, "Mouse", 80.00);

		// Salva as associações
		c1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		c2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(c1));
		p2.getCategorias().addAll(Arrays.asList(c1, c2));
		p3.getCategorias().addAll(Arrays.asList(c1));
		
		Estado e1 = new Estado(1, "Minas Gerais");
		Estado e2 = new Estado(2, "São Paulo");
		
		Cidade cdd1 = new Cidade(1, "Belo Horizonte", e1);
		Cidade cdd2 = new Cidade(2, "Sâo Paulo", e2);
		Cidade cdd3 = new Cidade(3, "Campinas", e2);
		
		e1.setCidades(Arrays.asList(cdd1));
		e2.setCidades(Arrays.asList(cdd2, cdd3));
		
		Cliente cli1 = new Cliente(1, "Maria Silva", "Maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("tel 1","tel 2"));		
		
		Endereco end1 = new Endereco(13, "Rua 1", "300", "Apt 1", "Bairro 1", "0213456789", cdd1, cli1);
		Endereco end2 = new Endereco(14, "Rua 2", "302", "Apt 2", "Bairro 2", "0213456789", cdd2, cli1);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));

		// S02A24 - Salva Pedidos ----
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(1, sdf.parse("30/09/2017 10:32"), cli1, end1);
		Pedido ped2 = new Pedido(2, sdf.parse("10/10/2017 19:42"), cli1, end2);		
		
		Pagamento pg1 = new PagamentoComCartao(1, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pg1);
		
		Pagamento pg2 = new PagamentoComBoleto(1, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null); 
		ped2.setPagamento(pg2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		// S02A26 - Cria, Mapeia e Salva os Itens de Pedido
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 2000.00, 1);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 80.00, 2);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 800.00, 1);		

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));		

		//RepositorioItemPedido.saveAll(Arrays.asList(ip1,ip2,ip3)); 
		
		// RepositorioPedido.saveAll(Arrays.asList(ped1, ped2));
		// RepositorioPagamento.saveAll(Arrays.asList(pg1, pg2));
		
		//new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		
		// Salva os endereços e clientes
		// RepositorioEndereco.saveAll(Arrays.asList(end1, end2));
		// RepositorioCliente.saveAll(Arrays.asList(cli1));
		
		// Salva os estados e cidades
		// RepositorioEstado.saveAll(Arrays.asList(e1, e2));
		// RepositorioCidade.saveAll(Arrays.asList(cdd1, cdd2, cdd3));
		
		// Salva os produtos
		// RepositorioCategoria.saveAll(Arrays.asList(c1, c2));
		// RepositorioProduto.saveAll(Arrays.asList(p1, p2, p3));
	}
}
