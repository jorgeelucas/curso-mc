package com.nelioalves.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.dto.ClienteDTO;
import com.nelioalves.cursomc.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResources {

	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		Cliente cliente = clienteService.obterPorId(id);
		return ResponseEntity.ok().body(cliente);
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@Valid @RequestBody ClienteDTO dto) {
		Cliente cliente = clienteService.fromDTO(dto);
		clienteService.atualizarCliente(cliente);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletarCliente(@PathVariable Integer id) {
		clienteService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/todos", method=RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> obterTodos() {
		List<Cliente> cliente = clienteService.obterTodos();
		List<ClienteDTO> dtoRetorno = cliente.stream().map(each -> new ClienteDTO(each)).collect(Collectors.toList());
		return ResponseEntity.ok().body(dtoRetorno);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> paginacao(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy,
			@RequestParam(value="directions", defaultValue="ASC") String directions
			) {
		Page<Cliente> clientePaginado = clienteService.obterPaginado(page, linesPerPage, directions, orderBy);
		Page<ClienteDTO> clienteDTO = clientePaginado.map(entidade -> new ClienteDTO(entidade));
		return ResponseEntity.ok().body(clienteDTO);
	}
	
}
