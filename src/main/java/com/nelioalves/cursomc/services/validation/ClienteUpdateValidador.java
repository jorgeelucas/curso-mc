package com.nelioalves.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.dto.ClienteDTO;
import com.nelioalves.cursomc.resources.exception.FieldMessages;
import com.nelioalves.cursomc.services.ClienteService;

public class ClienteUpdateValidador implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

	@Autowired
	private ClienteService clienteService;
	
	@Override
	public void initialize(ClienteUpdate ann) {
	}
	
	@Override
	public boolean isValid(ClienteDTO dto, ConstraintValidatorContext context) {
		List<FieldMessages> lista = new ArrayList<>();
		
		Cliente cliente = clienteService.obterPorId(dto.getId());
		
		if (cliente!=null && !cliente.getEmail().equals(dto.getEmail())) {
			lista.add(new FieldMessages("Email","Email já existe."));
		}
		
		for (FieldMessages error : lista) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(error.getMensagem()).addPropertyNode(error.getCampo())
					.addConstraintViolation();
		}
		return lista.isEmpty();
	}

}
