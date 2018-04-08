package com.nelioalves.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.nelioalves.cursomc.domain.Categoria;

public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private static final String NOT_EMPTY = "O campo é de preenchimento obrigatório.";
	private static final String LENTGH_MESSAGE = "O tamanho deve ser entre 5 e 80 caracteres.";
	
	@NotBlank(message=NOT_EMPTY)
	@Length(min=5, max=80, message=LENTGH_MESSAGE)
	private String nome;
	
	public CategoriaDTO() {
	}

	public CategoriaDTO(Categoria categoria) {
		this.id = categoria.getId();
		this.nome = categoria.getNome();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
