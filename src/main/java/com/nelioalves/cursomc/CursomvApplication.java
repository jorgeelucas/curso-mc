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
import com.nelioalves.cursomc.domain.Pagamento;
import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.domain.builder.ProdutoBuilder;
import com.nelioalves.cursomc.enums.EstadoPagamento;
import com.nelioalves.cursomc.enums.TipoCliente;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.repositories.CidadeRepository;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.repositories.EnderecoRepository;
import com.nelioalves.cursomc.repositories.EstadoRepository;
import com.nelioalves.cursomc.repositories.PagamentoRepository;
import com.nelioalves.cursomc.repositories.PedidoRepository;
import com.nelioalves.cursomc.repositories.ProdutoRepository;
import com.nelioalves.cursomc.resources.Pedido;

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
		
		categoriaRepository.saveAll(Arrays.asList(informatica, escritorio));
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
		mariaSilva.getTelefones().addAll(Arrays.asList("61992924768", "6199998888"));
		
		Endereco endereco1 = new Endereco("Rua Flores", "300", "apt 303", "jardim", "38220834", mariaSilva, uberlandia);
		Endereco endereco2 = new Endereco("Avenida Matos", "105", "sala 800", "centro", "38777012", mariaSilva, spCidade);		
		
		mariaSilva.getEnderecos().addAll(Arrays.asList(endereco1, endereco2));
		
		clienteRepository.saveAll(Arrays.asList(mariaSilva));
		enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		
		Pedido pedido1 = new Pedido(sdf.parse("30/09/2017 10:32"), mariaSilva, endereco1);
		
		Pedido pedido2 = new Pedido(sdf.parse("10/10/2017 19:35"), mariaSilva, endereco1);
		
		Pagamento pagamento1 = new Pagamento(EstadoPagamento.QUITADO.getCodigo(), pedido1);
		Pagamento pagamento2 = new Pagamento(EstadoPagamento.PENDENTE.getCodigo(), pedido2);
		
		pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2));
		pagamentoRepository.saveAll(Arrays.asList(pagamento1, pagamento2));
		
		
	}
}
