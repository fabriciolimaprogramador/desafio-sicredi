package br.com.fabricio.cooperativismo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.fabricio.cooperativismo.controller.request.PautaRequest;
import br.com.fabricio.cooperativismo.controller.response.PautaResponse;
import br.com.fabricio.cooperativismo.controller.response.SessaoResponse;
import br.com.fabricio.cooperativismo.exceptions.PautaNaoLocalizadaException;
import br.com.fabricio.cooperativismo.repository.AssociadoRepository;
import br.com.fabricio.cooperativismo.repository.PautaRepository;
import br.com.fabricio.cooperativismo.repository.SessaoRepository;
import br.com.fabricio.cooperativismo.repository.entity.PautaEntity;
import br.com.fabricio.cooperativismo.service.impl.PautaServiceImpl;

@SpringBootTest
public class PautaServiceTest {

	private static final Long ID_PAUTA = 1L;
	private static final String DESCRICAO_PAUTA = "Pauto do dia";
	private static final LocalDateTime ABERTURA_SESSCAO = LocalDateTime.of(2022, 12, 1, 12, 0, 0);
	private static final LocalDateTime FECHAMENTO_SESSCAO = LocalDateTime.of(2022, 12, 1, 12, 1, 0);
	private static final String STATUS_SESSAO = "Sessão aberta " + ABERTURA_SESSCAO;

	@InjectMocks
	private PautaServiceImpl pautaServiceImpl;

	@MockBean
	private PautaRepository pautaRepository;

	@MockBean
	private SessaoRepository sessaoRepository;

	@MockBean
	private AssociadoRepository associadoRepository;

	private PautaRequest pautaRequest;
	private PautaEntity pautaEntity;
	private Optional<PautaEntity> optionalPauta;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		start();
	}

	@Test
	void deveRetornarVotoResponseSucesso() throws Exception {

		Mockito.when(pautaRepository.findById(Mockito.any())).thenReturn(optionalPauta);

		SessaoResponse response = pautaServiceImpl.abrirSesscao(ID_PAUTA);
		Assertions.assertNotNull(response);
		Assertions.assertEquals(SessaoResponse.class, response.getClass());

	}

	@Test
	void deveRetornarSessaoResponseAoAbrirSessaoComSucesso() throws Exception {

		Mockito.when(pautaRepository.findById(Mockito.any())).thenReturn(optionalPauta);

		SessaoResponse response = pautaServiceImpl.abrirSesscao(ID_PAUTA);
		Assertions.assertNotNull(response);
		Assertions.assertEquals(SessaoResponse.class, response.getClass());
//		Assertions.assertEquals(STATUS_SESSAO, response.getStatus());

	}

	@Test
	void deveRetornarPautaNaoLocalizadaExceptionAoAbrirSessao() throws Exception {

		Mockito.when(pautaRepository.findById(Mockito.any()))
				.thenThrow(new PautaNaoLocalizadaException("Pauta não encontrada"));

		try {
			pautaServiceImpl.abrirSesscao(ID_PAUTA);
		} catch (Exception e) {
			Assertions.assertEquals(PautaNaoLocalizadaException.class, e.getClass());
			Assertions.assertEquals("Pauta não encontrada", e.getMessage());
		}

	}

	@Test
	void deveRetornarSucessoAoInserirNovaPauta() throws Exception {

		Mockito.when(pautaRepository.save(Mockito.any())).thenReturn(pautaEntity);

		PautaResponse response = pautaServiceImpl.insert(pautaRequest);

		Assertions.assertNotNull(response);
		Assertions.assertEquals(PautaResponse.class, response.getClass());
		Assertions.assertEquals(ID_PAUTA, response.getId());
		Assertions.assertEquals(DESCRICAO_PAUTA, response.getDescricao());

	}

	private void start() {
		pautaRequest = new PautaRequest(DESCRICAO_PAUTA);

		pautaEntity = new PautaEntity();
		pautaEntity.setId(ID_PAUTA);
		pautaEntity.setDescricao(DESCRICAO_PAUTA);
		pautaEntity.setAbertura(ABERTURA_SESSCAO);
		pautaEntity.setFechamento(FECHAMENTO_SESSCAO);

		optionalPauta = Optional.of(pautaEntity);

	}

}
