package com.nelioalves.cursomc.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StringUtils {

	private StringUtils() {
	}
	
	public static String decodeName(String nome) {
		try {
			return URLDecoder.decode(nome, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	public static List<Integer> obterlistaDeIds(String ids){
		List<Integer> lista = new ArrayList<>();
		try {
			for (String id : ids.split(",")) {
				lista.add(Integer.parseInt(id));
			}
		} catch(NumberFormatException e) {
			return Collections.emptyList();
		}
		return lista;
	}
	
}
