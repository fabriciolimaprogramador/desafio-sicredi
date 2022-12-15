package br.com.fabricio.cooperativismo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fabricio.cooperativismo.controller.request.PautaRequest;
import br.com.fabricio.cooperativismo.controller.request.VotoRequest;
import br.com.fabricio.cooperativismo.controller.response.PautaResponse;
import br.com.fabricio.cooperativismo.controller.response.ResultadoVotacaoResponse;
import br.com.fabricio.cooperativismo.controller.response.SessaoResponse;
import br.com.fabricio.cooperativismo.controller.response.VotoResponse;
import br.com.fabricio.cooperativismo.service.PautaService;

@RestController
@RequestMapping("/api/v1/pauta")
public class PautaController {
	
	@Autowired
	private PautaService pautaService;
	
	@PostMapping
	public PautaResponse cadastraNovaPauta(@Valid @RequestBody PautaRequest pautaRequest) {
		return pautaService.insert(pautaRequest);
	}

	@PutMapping("/{idPauta}/abrir-sessao")
	public SessaoResponse abrirSesscaoDeVotacao(@PathVariable Long idPauta) throws Exception {
		return pautaService.abrirSesscao(idPauta);
	}
	
	@PostMapping("/{idPauta}/votacao")
	public VotoResponse receberVotos(@PathVariable Long idPauta, @RequestBody VotoRequest votoRequest) throws Exception {
		return pautaService.votar(idPauta, votoRequest);
	}
	
	@GetMapping("/{idPauta}/contabilizar-votos")
	public ResultadoVotacaoResponse contabilizarVotos(@PathVariable Long idPauta) throws Exception {
		return pautaService.getResultadoVotacao(idPauta);
	}

	

}
