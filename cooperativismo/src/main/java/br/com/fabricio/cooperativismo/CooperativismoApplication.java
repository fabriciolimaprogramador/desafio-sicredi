package br.com.fabricio.cooperativismo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CooperativismoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CooperativismoApplication.class, args);
	}

}

/*

Conheço três formas de versionar APIs.

1 - Colocar a versão no path da URL(endpoint).
htt://dominio.com.br/api/v1/recurso
2 - Colocar a versão no Header(cabeçalho) da requisição.
htt://dominio.com.br/api/recurso
Version: v1
3 - Colocar a versão como parâmetro Query String.
htt://dominio.com.br/api/recurso?versao=v1

Prefiro a primeira opção.

 */