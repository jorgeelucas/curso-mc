package com.nelioalves.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.nelioalves.cursomc.dto.ClienteNewDTO;
import com.nelioalves.cursomc.enums.TipoCliente;
import com.nelioalves.cursomc.resources.exception.FieldMessages;
import com.nelioalves.cursomc.services.validation.utils.BR;

public class ValidadorCPFeCNPJValidador implements ConstraintValidator<ValidadorCPFeCNPJ, ClienteNewDTO> {
	@Override
	public void initialize(ValidadorCPFeCNPJ ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessages> list = new ArrayList<>();

		if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCodigo()) && !BR.isValidCpf(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessages("CpfOuCnpj","CPF inválido."));
		}
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCodigo()) && !BR.isValidCnpj(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessages("CpfOuCnpj","CNPJ inválido."));
		}
		
		
		for (FieldMessages e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMensagem()).addPropertyNode(e.getCampo())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}