package com.nelioalves.cursomc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Cidade;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.Endereco;
import com.nelioalves.cursomc.domain.Estado;
import com.nelioalves.cursomc.domain.ItemPedido;
import com.nelioalves.cursomc.domain.Pagamento;
import com.nelioalves.cursomc.domain.PagamentoComBoleto;
import com.nelioalves.cursomc.domain.PagamentoComCartao;
import com.nelioalves.cursomc.domain.Pedido;
import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.domain.builder.ProdutoBuilder;
import com.nelioalves.cursomc.enums.EstadoPagamento;
import com.nelioalves.cursomc.enums.TipoCliente;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.repositories.CidadeRepository;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.repositories.EnderecoRepository;
import com.nelioalves.cursomc.repositories.EstadoRepository;
import com.nelioalves.cursomc.repositories.ItemPedidoRepository;
import com.nelioalves.cursomc.repositories.PagamentoRepository;
import com.nelioalves.cursomc.repositories.PedidoRepository;
import com.nelioalves.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomvApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomvApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		cargaInicial();
	}
	
	public void cargaInicial() throws ParseException {
		Categoria informatica = new Categoria(null, "Informática");
		Categoria escritorio = new Categoria(null, "Escritório");
		Categoria cat1 = new Categoria(null, "Jardinagem");
		Categoria cat2 = new Categoria(null, "Cozinha");
		Categoria cat3 = new Categoria(null, "Casa");
		Categoria cat4 = new Categoria(null, "Banheiro");
		Categoria cat5 = new Categoria(null, "Carro");
		Categoria cat6 = new Categoria(null, "Imovel");
		Categoria cat7 = new Categoria(null, "Roupas");
		Categoria cat8 = new Categoria(null, "Cidade");
		Categoria cat9 = new Categoria(null, "Teste");
		Categoria cat10 = new Categoria(null, "Inhumas");
		Categoria cat11 = new Categoria(null, "Armario");
		
		Produto computador = new ProdutoBuilder()
				.comNome("Computador")
				.comPreco(2000.00)
				.comCategorias(Arrays.asList(informatica)).build();
		
		Produto impressora = new ProdutoBuilder()
				.comNome("Impressora")
				.comPreco(800.00)
				.comCategorias(Arrays.asList(informatica, escritorio)).build();
		
		Produto mouse = new ProdutoBuilder()
				.comNome("Mouse")
				.comPreco(80.00)
				.comCategorias(Arrays.asList(informatica)).build();
		
		informatica.setProdutos(Arrays.asList(computador, impressora, mouse));
		
		escritorio.setProdutos(Arrays.asList(impressora));
		
		categoriaRepository.saveAll(Arrays.asList(informatica, escritorio, cat1, cat2, cat3, cat4, cat4, cat5, cat6, cat7, cat8, cat9, cat10, cat11));
		produtoRepository.saveAll(Arrays.asList(computador, impressora, mouse));
		
		Estado minas = new Estado("Minas Gerais");
		Estado spEstado = new Estado("São Paulo");
		
		Cidade uberlandia = new Cidade("Uberlandia", minas);
		Cidade spCidade = new Cidade("São Paulo", spEstado);
		Cidade campinas = new Cidade("Campinas", spEstado);
		
		minas.getCidades().addAll(Arrays.asList(uberlandia));
		spEstado.getCidades().addAll(Arrays.asList(spCidade, campinas));
		
		estadoRepository.saveAll(Arrays.asList(minas, spEstado));
		cidadeRepository.saveAll(Arrays.asList(uberlandia, spCidade, campinas));
		
		Cliente mariaSilva = new Cliente("Maria Silva", "maria@gmail.com", "3637912377", TipoCliente.PESSOAFISICA);
		mariaSilva.addTelefones("61992924768", "6199998888");
		
		Endereco endereco1 = new Endereco("Rua Flores", "300", "apt 303", "jardim", "38220834", mariaSilva, uberlandia);
		Endereco endereco2 = new Endereco("Avenida Matos", "105", "sala 800", "centro", "38777012", mariaSilva, spCidade);		
		
		mariaSilva.addEndereco(endereco1);
		mariaSilva.addEndereco(endereco2);
		
		clienteRepository.saveAll(Arrays.asList(mariaSilva));
		enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		
		Pedido pedido1 = new Pedido(sdf.parse("30/09/2017 10:32"), mariaSilva, endereco1);
		
		Pedido pedido2 = new Pedido(sdf.parse("10/10/2017 19:35"), mariaSilva, endereco1);
		
		Pagamento pagamento1 = new PagamentoComCartao(EstadoPagamento.QUITADO, pedido1, 6);
		pedido1.setPagamento(pagamento1);
		
		Pagamento pagamento2 = new PagamentoComBoleto(EstadoPagamento.PENDENTE, pedido2, sdf.parse("20/10/2017 00:00"), null);
		pedido2.setPagamento(pagamento2);
		
		mariaSilva.addAllPedidos(Arrays.asList(pedido1, pedido2));
		
		pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2));
		pagamentoRepository.saveAll(Arrays.asList(pagamento1, pagamento2));
		
		ItemPedido ip1 = new ItemPedido(pedido1, computador, 0.00, 1, 2000.0);
		ItemPedido ip2 = new ItemPedido(pedido1, mouse, 0.00, 2, 80.0);
		ItemPedido ip3 = new ItemPedido(pedido2, impressora, 100.0, 1, 800.00);
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2, ip3));
		
		pedido1.addAllItens(ip1,ip2);
		pedido2.addAllItens(ip3);
		
		computador.addAllItens(ip1);
		mouse.addAllItens(ip2);
		impressora.addAllItens(ip3);
		
	}
}
