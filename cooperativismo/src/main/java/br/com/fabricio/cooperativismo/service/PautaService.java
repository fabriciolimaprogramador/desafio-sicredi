package br.com.fabricio.cooperativismo.service;

import br.com.fabricio.cooperativismo.controller.request.PautaRequest;
import br.com.fabricio.cooperativismo.controller.request.VotoRequest;
import br.com.fabricio.cooperativismo.controller.response.PautaResponse;
import br.com.fabricio.cooperativismo.controller.response.ResultadoVotacaoResponse;
import br.com.fabricio.cooperativismo.controller.response.SessaoResponse;
import br.com.fabricio.cooperativismo.controller.response.VotoResponse;

public interface PautaService {

	PautaResponse insert(PautaRequest pautaRequest);

	VotoResponse votar(Long idPauta, VotoRequest votoRequest) throws Exception;

	ResultadoVotacaoResponse getResultadoVotacao(Long idPauta) throws Exception;

	SessaoResponse abrirSesscao(Long idPauta) throws Exception;

}
