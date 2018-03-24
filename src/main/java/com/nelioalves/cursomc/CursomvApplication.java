package com.nelioalves.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.repositories.CategoriaRepository;

@SpringBootApplication
public class CursomvApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomvApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		inserindoCategorias();
	}
	
	public void inserindoCategorias() {
		Categoria informatica = new Categoria(null, "Informática");
		Categoria escritorio = new Categoria(null, "Escritório");
		repo.saveAll(Arrays.asList(informatica, escritorio));
	}
}
