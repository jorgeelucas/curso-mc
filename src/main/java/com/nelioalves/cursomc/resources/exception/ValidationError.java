package com.nelioalves.cursomc.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardException {

	private List<FieldMessages> erros = new ArrayList<>();
	
	public ValidationError(Integer status, String msg, Long timestamp) {
		super(status, msg, timestamp);
	}

	public List<FieldMessages> getErros() {
		return erros;
	}

	public void setErros(List<FieldMessages> erros) {
		this.erros = erros;
	}

	public void addErros(FieldMessages erro) {
		this.erros.add(erro);
	}
	
}
