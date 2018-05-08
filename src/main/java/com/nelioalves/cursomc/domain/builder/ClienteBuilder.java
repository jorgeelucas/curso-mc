package com.nelioalves.cursomc.domain.builder;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.Endereco;
import com.nelioalves.cursomc.domain.Pedido;
import com.nelioalves.cursomc.enums.TipoCliente;

public class ClienteBuilder {

	private Cliente cliente;
	
	public ClienteBuilder() {
		this.cliente = new Cliente();
	}
	
	public Cliente construir() {
		return this.cliente;
	}
	
	public ClienteBuilder comId(Integer id) {
		this.cliente.setId(id);
		return this;
	}
	
	public ClienteBuilder comNome(String nome) {
		this.cliente.setNome(nome);
		return this;
	}
	
	public ClienteBuilder comEmail(String email) {
		this.cliente.setEmail(email);
		return this;
	}
	
	public ClienteBuilder comCpfOuCnpj(String cpfOuCnpj) {
		this.cliente.setCpfOuCnpj(cpfOuCnpj);
		return this;
	}
	
	public ClienteBuilder comTipo(Integer tipo) {
		this.cliente.setTipo(TipoCliente.toEnum(tipo));
		return this;
	}
	
	public ClienteBuilder comTipo(TipoCliente tipo) {
		this.cliente.setTipo(tipo);
		return this;
	}
	
	public ClienteBuilder comSenha(String senha) {
		this.cliente.setSenha(senha);
		return this;
	}
	
	public ClienteBuilder comEnderecos(List<Endereco> enderecos) {
		this.cliente.setEnderecos(enderecos);
		return this;
	}
	
	public ClienteBuilder comTelefones(Set<String> telefones) {
		this.cliente.setTelefones(telefones);
		return this;
	}
	
	public ClienteBuilder comPedidos(List<Pedido> pedidos) {
		this.cliente.setPedidos(pedidos);
		return this;
	}
	
	
}
