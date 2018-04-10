package com.nelioalves.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.services.validation.ClienteUpdate;

@ClienteUpdate
public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotBlank(message="Preenchimento obrigatório")
	@Pattern(regexp = "^\\d", message="id incorreto")
	private String id;
	@NotBlank
	@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	@NotBlank
	@Email
	private String email;

	public ClienteDTO() {
	}
	
	public ClienteDTO(Cliente cliente) {
		this.id = String.valueOf(cliente.getId());
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
	}

	public ClienteDTO(Integer id, String nome, String email) {
		this.setId(id);
		this.setNome(nome);
		this.setEmail(email);
	}

	public Integer getId() {
		return Integer.parseInt(id);
	}

	public void setId(Integer id) {
		this.id = String.valueOf(id);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
