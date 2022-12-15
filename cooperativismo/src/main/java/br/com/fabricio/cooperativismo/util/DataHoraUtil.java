package br.com.fabricio.cooperativismo.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataHoraUtil {
	
	private static final String DATA_HORA_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static LocalDateTime format(LocalDateTime value) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATA_HORA_FORMAT);
		String text = value.format(formatter);
		return LocalDateTime.parse(text, formatter);
	}

}
