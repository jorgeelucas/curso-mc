package com.nelioalves.cursomc.domain.builder;

import java.util.List;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Produto;

public class CategoriaBuilder {

	private Categoria categoria;
	
	public CategoriaBuilder() {
		categoria = new Categoria();
	}
	
	public Categoria build() {
		return categoria;
	}
	
	public CategoriaBuilder comId(Integer id) {
		categoria.setId(id);
		return this;
	}
	
	public CategoriaBuilder comNome(String nome) {
		categoria.setNome(nome);
		return this;
	}
	
	public CategoriaBuilder comProdutos(List<Produto> produtos) {
		categoria.getProdutos().addAll(produtos);
		return this;
	}
}
