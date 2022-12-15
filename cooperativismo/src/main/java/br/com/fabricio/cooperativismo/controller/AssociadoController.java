package br.com.fabricio.cooperativismo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fabricio.cooperativismo.controller.request.AssociadoRequest;
import br.com.fabricio.cooperativismo.controller.response.AssociadoResponse;
import br.com.fabricio.cooperativismo.service.AssociadoService;

@RestController
@RequestMapping("/api/v1/associado")
public class AssociadoController {
	
	@Autowired
	private AssociadoService associadoService;
	
	@PostMapping
	public ResponseEntity<AssociadoResponse> insert(@Valid @RequestBody AssociadoRequest associadoRequest) throws Exception {
		return ResponseEntity.status(HttpStatus.CREATED).body(associadoService.insert(associadoRequest));
	}
	
	@GetMapping("/{idAssociado}")
	public ResponseEntity<AssociadoResponse> findById(@PathVariable Long idAssociado) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(associadoService.findById(idAssociado));
	}
	
	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<AssociadoResponse> findByCpf(@PathVariable String cpf) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(associadoService.findByCpf(cpf));
	}
	
	

}
