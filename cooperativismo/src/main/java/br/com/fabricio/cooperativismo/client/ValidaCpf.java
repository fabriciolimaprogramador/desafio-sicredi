package br.com.fabricio.cooperativismo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ValidaCpf", url = "${url.validaCpf}")
public interface ValidaCpf {
	
	@GetMapping("/users/{cpf}")
	CpfValido validarCpf(@PathVariable String cpf);

}
