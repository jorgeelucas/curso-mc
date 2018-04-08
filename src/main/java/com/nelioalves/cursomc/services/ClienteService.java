package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Cidade;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.Endereco;
import com.nelioalves.cursomc.dto.ClienteDTO;
import com.nelioalves.cursomc.dto.ClienteNewDTO;
import com.nelioalves.cursomc.enums.TipoCliente;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.repositories.EnderecoRepository;
import com.nelioalves.cursomc.services.exceptions.DataIntegrityViolation;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Cliente obterPorId(Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> 
					new ObjectNotFoundException(Cliente.class.getName() + " de id " + id + " não encontrado"));
	}
	
	@Transactional
	public Cliente inserir(Cliente cliente) {
		Cliente clienteRetorno = clienteRepository.save(cliente);
		enderecoRepository.saveAll(cliente.getEnderecos());
		return clienteRetorno;
	}
	
	public void atualizarCliente(Cliente cliente) {
		Cliente novoCliente = obterPorId(cliente.getId());
		atualizarCampos(novoCliente, cliente);
		clienteRepository.save(novoCliente);
	}

	private void atualizarCampos(Cliente novoCliente, Cliente cliente) {
		novoCliente.setNome(cliente.getNome());
		novoCliente.setEmail(cliente.getEmail());
	}

	public Cliente fromDTO(ClienteDTO dto) {
		return new Cliente(dto.getId(), dto.getNome(), dto.getEmail());
	}
	
	public Cliente fromDTO(ClienteNewDTO dto) {
		Cliente cliente = new Cliente(dto.getNome(), dto.getEmail(), dto.getCpfOuCnpj(), TipoCliente.toEnum(dto.getTipo()));
		Cidade cidade = new Cidade(dto.getIdCidade());
		Endereco endereco = new Endereco(dto.getLogradouro(), 
				dto.getNumero(), 
				dto.getComplemento(), 
				dto.getBairro(), 
				dto.getCep(), 
				cliente, 
				cidade);
		cliente.addEndereco(endereco);
		cliente.addTelefones(dto.getTelefone1(), dto.getTelefone2(), dto.getTelefone3());
		return cliente;
	}

	public Page<Cliente> obterPaginado(Integer page, Integer linesPerPage, String directions, String orderBy) {
		PageRequest paginado = PageRequest.of(page, linesPerPage, Direction.valueOf(directions), orderBy);
		return clienteRepository.findAll(paginado);
	}

	public List<Cliente> obterTodos() {
		return clienteRepository.findAll();
	}

	public void deletar(Integer id) {
		try {
			clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException e ) {
			throw new DataIntegrityViolation("impossível deletar cliente, há entidades relacionadas");
		}
	}
	
}
