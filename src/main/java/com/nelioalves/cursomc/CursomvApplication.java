package com.nelioalves.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Cidade;
import com.nelioalves.cursomc.domain.Estado;
import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.domain.builder.ProdutoBuilder;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.repositories.CidadeRepository;
import com.nelioalves.cursomc.repositories.EstadoRepository;
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
	
	public static void main(String[] args) {
		SpringApplication.run(CursomvApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		cargaInicial();
	}
	
	public void cargaInicial() {
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
	}
}
