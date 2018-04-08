package com.nelioalves.cursomc.resources.exception;

import java.io.Serializable;

public class FieldMessages implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String campo;
	private String mensagem;
	
	public FieldMessages() {
	}

	public FieldMessages(String campo, String mensagem) {
		this.campo = campo;
		this.mensagem = mensagem;
	}
	
	public String getCampo() {
		return campo;
	}
	public void setCampo(String campo) {
		this.campo = campo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
