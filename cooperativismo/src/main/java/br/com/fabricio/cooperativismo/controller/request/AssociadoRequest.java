package br.com.fabricio.cooperativismo.controller.request;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class AssociadoRequest {

	@NotBlank
	@Length(min = 11, max = 11)
	private String cpf;

	private String nome;

}
