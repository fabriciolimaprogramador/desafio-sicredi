package br.com.fabricio.cooperativismo.exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErroDetectado {

	private LocalDateTime dataHora;
	private String mensagem;

}
