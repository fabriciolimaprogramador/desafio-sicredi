package br.com.fabricio.cooperativismo.controller.request;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AssociadoRequest {

	@NotBlank
	@Length(min = 11, max = 11)
	private String cpf;

	private String nome;

}
