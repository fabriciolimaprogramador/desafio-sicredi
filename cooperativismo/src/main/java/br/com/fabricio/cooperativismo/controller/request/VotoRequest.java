package br.com.fabricio.cooperativismo.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class VotoRequest {

	private Long idAssociado;
	private String voto;

}
