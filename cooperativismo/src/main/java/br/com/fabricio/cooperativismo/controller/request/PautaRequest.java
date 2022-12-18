package br.com.fabricio.cooperativismo.controller.request;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PautaRequest {
	
	@NotBlank
	@Length(min = 3, max = 255)
	private String descricao;

}
