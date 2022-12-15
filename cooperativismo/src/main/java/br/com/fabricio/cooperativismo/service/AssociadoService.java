package br.com.fabricio.cooperativismo.service;

import br.com.fabricio.cooperativismo.controller.request.AssociadoRequest;
import br.com.fabricio.cooperativismo.controller.response.AssociadoResponse;
import br.com.fabricio.cooperativismo.exceptions.AssociadoNotFoundException;

public interface AssociadoService {

	AssociadoResponse insert(AssociadoRequest associadoRequest) throws Exception;

	AssociadoResponse findById(Long idAssociado) throws AssociadoNotFoundException;

	AssociadoResponse findByCpf(String cpf) throws AssociadoNotFoundException;

}
