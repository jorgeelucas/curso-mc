package com.nelioalves.cursomc.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class BoletoService {

	public Date getDataPagamento(Date dataGeracao) {
		LocalDate ldate = dataGeracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		ldate = ldate.plusDays(7);
		Instant instant = Instant.from(ldate.atStartOfDay(ZoneId.systemDefault()));
		return Date.from(instant);
	}
	
}
