package br.com.fabricio.cooperativismo.util;

public class SomentoNumeros {

	public static String format(String valor) {
		return valor.replaceAll("[^0-9]", "");
	}

}
