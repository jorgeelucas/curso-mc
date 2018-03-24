package com.nelioalves.cursomc.domain.builder;

import java.util.List;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Produto;

public class ProdutoBuilder {

	private Produto produto;
	
	public ProdutoBuilder() {
		produto = new Produto();
	}
	
	public Produto build() {
		return produto;
	}
	
	public ProdutoBuilder comId(Integer id) {
		this.produto.setId(id);
		return this;
	}
	
	public ProdutoBuilder comNome(String nome) {
		this.produto.setNome(nome);
		return this;
	}
	
	public ProdutoBuilder comPreco(Double preco) {
		this.produto.setPreco(preco);
		return this;
	}
	
	public ProdutoBuilder comCategorias(List<Categoria> categorias) {
		this.produto.getCategorias().addAll(categorias);
		return this;
	}
	
}
