package br.com.fabricio.cooperativismo.service.mapper;

import br.com.fabricio.cooperativismo.controller.request.AssociadoRequest;
import br.com.fabricio.cooperativismo.controller.response.AssociadoResponse;
import br.com.fabricio.cooperativismo.repository.entity.AssociadoEntity;
import br.com.fabricio.cooperativismo.util.SomentoNumeros;

public class AssociadoMapper {
	
	public static AssociadoEntity toEntity(AssociadoRequest associadoRequest) {
		AssociadoEntity associadoEntity = new AssociadoEntity();
		associadoEntity.setCpf(SomentoNumeros.format(associadoRequest.getCpf()));
		associadoEntity.setNome(associadoRequest.getNome() == null ? null : associadoRequest.getNome().toUpperCase());
		return associadoEntity;
	}

	public static AssociadoResponse toResponse(AssociadoEntity associadoEntity) {
		return new AssociadoResponse(associadoEntity.getId(), associadoEntity.getCpf(), associadoEntity.getNome());
	}

}
