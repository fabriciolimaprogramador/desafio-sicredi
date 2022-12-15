package br.com.fabricio.cooperativismo.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AssociadoResponse {
	
	private Long id;
	private String cpf;
	private String nome;

}
