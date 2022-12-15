package br.com.fabricio.cooperativismo.service.mapper;

import br.com.fabricio.cooperativismo.controller.request.PautaRequest;
import br.com.fabricio.cooperativismo.controller.response.PautaResponse;
import br.com.fabricio.cooperativismo.repository.entity.PautaEntity;

public class PautaMapper {
	
	public static PautaEntity toEntity(PautaRequest pautaRequest) {
		PautaEntity pautaEntity = new PautaEntity();
		pautaEntity.setDescricao(pautaRequest.getDescricao() == null ? null : pautaRequest.getDescricao().toUpperCase());
		return pautaEntity;
	}

	public static PautaResponse toResponse(PautaEntity pautaEntity) {
		return new PautaResponse(pautaEntity.getId(), pautaEntity.getDescricao());
	}

}
