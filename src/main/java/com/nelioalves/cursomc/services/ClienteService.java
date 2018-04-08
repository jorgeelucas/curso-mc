package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.dto.ClienteDTO;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.services.exceptions.DataIntegrityViolation;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente obterPorId(Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> 
					new ObjectNotFoundException(Cliente.class.getName() + " de id " + id + " não encontrado"));
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
