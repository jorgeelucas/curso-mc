package com.nelioalves.cursomc.enums;

public enum EstadoPagamento {

	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private Integer codigo;
	private String estado;
	
	private EstadoPagamento(Integer codigo, String estado) {
		this.codigo = codigo;
		this.estado = estado;
	}
	
	public Integer getCodigo() {
		return this.codigo;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public static EstadoPagamento toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}
		
		for(EstadoPagamento x : EstadoPagamento.values()) {
			if (codigo.equals(x.getCodigo())) {
				return x;
			}
		}
		throw new IllegalArgumentException("codigo inválido: "+codigo);
		
	}
	
}
