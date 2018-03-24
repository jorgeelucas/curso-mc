package com.nelioalves.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.cursomc.domain.Categoria;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResources {

	@RequestMapping(method=RequestMethod.GET)
	public List<Categoria> listar() {
		
		Categoria informatica = new Categoria(1, "informatica");
		Categoria escritorio = new Categoria(2, "escritório");
		
		List<Categoria> lista = new ArrayList<>();
		lista.add(informatica);
		lista.add(escritorio);
		
		
		return lista;
	}
	
}
