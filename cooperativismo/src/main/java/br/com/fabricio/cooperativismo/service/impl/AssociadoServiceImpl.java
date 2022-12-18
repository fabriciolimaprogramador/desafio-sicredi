package br.com.fabricio.cooperativismo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fabricio.cooperativismo.controller.request.AssociadoRequest;
import br.com.fabricio.cooperativismo.controller.response.AssociadoResponse;
import br.com.fabricio.cooperativismo.exceptions.AssociadoJaCadastradoException;
import br.com.fabricio.cooperativismo.exceptions.AssociadoNotFoundException;
import br.com.fabricio.cooperativismo.repository.AssociadoRepository;
import br.com.fabricio.cooperativismo.repository.entity.AssociadoEntity;
import br.com.fabricio.cooperativismo.service.AssociadoService;
import br.com.fabricio.cooperativismo.service.mapper.AssociadoMapper;

@Service
public class AssociadoServiceImpl implements AssociadoService {

	@Autowired
	private AssociadoRepository associadoRepository;

	@Override
	@Transactional
	public AssociadoResponse insert(AssociadoRequest associadoRequest)  {

		AssociadoEntity associadoEntity = associadoRepository.findByCpf(associadoRequest.getCpf());
		if (associadoEntity != null) {
			throw new AssociadoJaCadastradoException("Associado já cadastrado");
		}

		associadoEntity = AssociadoMapper.toEntity(associadoRequest);
		AssociadoEntity associadoSalvo = associadoRepository.save(associadoEntity);
		return AssociadoMapper.toResponse(associadoSalvo);
	}

	@Override
	public AssociadoResponse findById(Long idAssociado) throws AssociadoNotFoundException {

		Optional<AssociadoEntity> associado = associadoRepository.findById(idAssociado);
		if (associado.isEmpty()) {
			throw new AssociadoNotFoundException("Associado não localizado");
		}

		return AssociadoMapper.toResponse(associado.get());

	}

	@Override
	public AssociadoResponse findByCpf(String cpf) throws AssociadoNotFoundException {

		AssociadoEntity associado = associadoRepository.findByCpf(cpf);
		if (associado == null) {
			throw new AssociadoNotFoundException("Associado não localizado");
		}

		return AssociadoMapper.toResponse(associado);
	}

}
