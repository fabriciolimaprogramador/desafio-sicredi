package br.com.fabricio.cooperativismo.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fabricio.cooperativismo.controller.request.PautaRequest;
import br.com.fabricio.cooperativismo.controller.request.VotoRequest;
import br.com.fabricio.cooperativismo.controller.response.PautaResponse;
import br.com.fabricio.cooperativismo.controller.response.ResultadoVotacaoResponse;
import br.com.fabricio.cooperativismo.controller.response.SessaoResponse;
import br.com.fabricio.cooperativismo.controller.response.VotoResponse;
import br.com.fabricio.cooperativismo.exceptions.AssociadoNotFoundException;
import br.com.fabricio.cooperativismo.repository.AssociadoRepository;
import br.com.fabricio.cooperativismo.repository.PautaRepository;
import br.com.fabricio.cooperativismo.repository.SessaoRepository;
import br.com.fabricio.cooperativismo.repository.entity.AssociadoEntity;
import br.com.fabricio.cooperativismo.repository.entity.PautaEntity;
import br.com.fabricio.cooperativismo.repository.entity.SessaoEntity;
import br.com.fabricio.cooperativismo.service.PautaService;
import br.com.fabricio.cooperativismo.service.mapper.PautaMapper;
import br.com.fabricio.cooperativismo.util.DataHoraUtil;

@Service
public class PautaServiceImpl implements PautaService {

	@Autowired
	private PautaRepository pautaRepository;

	@Autowired
	private SessaoRepository sessaoRepository;

	@Autowired
	private AssociadoRepository associadoRepository;

	// A linha abaixo foi comentada porque, o consumo da API de validação de CPF
	// está fora do ar.
//	@Autowired private ValidaCpf validaCpf;

	@Value("${sessao.tempoDeDuracao}")
	private int tempoFechamentoSessao;

	@Override
	@Transactional
	public PautaResponse insert(PautaRequest pautaRequest) {
		PautaEntity pautaEntity = PautaMapper.toEntity(pautaRequest);
		PautaEntity pautaSalvo = pautaRepository.save(pautaEntity);
		return PautaMapper.toResponse(pautaSalvo);
	}

	@Override
	@Transactional
	public VotoResponse votar(Long idPauta, VotoRequest votoRequest) throws Exception {

		Optional<PautaEntity> pauta = pautaRepository.findById(idPauta);
		if (pauta.isEmpty()) {
			throw new Exception("Pauta não encontrada");
		}

		Optional<AssociadoEntity> associado = associadoRepository.findById(votoRequest.getIdAssociado());
		if (associado.isEmpty()) {
			throw new AssociadoNotFoundException("Associado não encontrada");
		}

		// A linha abaixo foi comentada porque, o consumo da API de validação de CPF
		// está fora do ar.
//		CpfValido cpfValido = validaCpf.validarCpf(associado.get().getCpf());
//		if (cpfValido.getStatus().equals("UNABLE_TO_VOTE")) {
//			throw new CpfAssociadoInvalidoException("CPF Associado está inválido");
//		}

		SessaoEntity sessao = sessaoRepository.findByIdPautaAndIdAssociado(idPauta, votoRequest.getIdAssociado());
		if (sessao != null) {
			throw new Exception("Voto já cadastrado");
		}

		if (sessaoFechada(pauta.get())) {
			throw new Exception("Sessão fechada");
		}

		SessaoEntity sessaoEntity = new SessaoEntity(idPauta, votoRequest.getIdAssociado(),
				votoRequest.getVoto().toLowerCase());
		sessaoRepository.save(sessaoEntity);

		return new VotoResponse("Voto realizado com sucesso");
	}

	@Override
	public ResultadoVotacaoResponse getResultadoVotacao(Long idPauta) throws Exception {

		Optional<PautaEntity> pauta = pautaRepository.findById(idPauta);
		if (pauta.isEmpty()) {
			throw new Exception("Pauta não encontrada");
		}

		List<SessaoEntity> sessoesPorPauta = sessaoRepository.findByIdPauta(idPauta);
		if (sessoesPorPauta.size() == 0) {
			throw new Exception("Não há nenhuma sessão para pauta " + idPauta);
		}

		int contadorSim = 0;
		int contadorNao = 0;
		for (SessaoEntity sessaoEntity : sessoesPorPauta) {

			if (sessaoEntity.getVoto().equals("sim")) {
				contadorSim++;
			} else {
				contadorNao++;
			}

		}

		ResultadoVotacaoResponse resultado = new ResultadoVotacaoResponse();
		resultado.setNao(contadorNao);
		resultado.setSim(contadorSim);

		return resultado;

	}

	@Override
	@Transactional
	public SessaoResponse abrirSesscao(Long idPauta) throws Exception {

		Optional<PautaEntity> pauta = pautaRepository.findById(idPauta);
		if (pauta.isEmpty()) {
			throw new Exception("Pauta não encontrada");
		}

		PautaEntity pautaEntity = pauta.get();
		pautaEntity.setAbertura(LocalDateTime.now());
		pautaEntity.setFechamento(pautaEntity.getAbertura().plusMinutes(tempoFechamentoSessao));
		pautaRepository.save(pautaEntity);

		SessaoResponse sessaoResponse = new SessaoResponse();
		sessaoResponse.setStatus("Sessão aberta " + DataHoraUtil.format(pautaEntity.getAbertura()));
		return sessaoResponse;

	}

	private boolean sessaoFechada(PautaEntity pautaEntity) throws Exception {
		return LocalDateTime.now().isAfter(pautaEntity.getFechamento());
	}

}
