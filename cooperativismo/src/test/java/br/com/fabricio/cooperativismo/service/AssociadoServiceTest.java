package br.com.fabricio.cooperativismo.service;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.fabricio.cooperativismo.controller.request.AssociadoRequest;
import br.com.fabricio.cooperativismo.controller.response.AssociadoResponse;
import br.com.fabricio.cooperativismo.exceptions.AssociadoJaCadastradoException;
import br.com.fabricio.cooperativismo.exceptions.AssociadoNotFoundException;
import br.com.fabricio.cooperativismo.repository.AssociadoRepository;
import br.com.fabricio.cooperativismo.repository.entity.AssociadoEntity;
import br.com.fabricio.cooperativismo.service.impl.AssociadoServiceImpl;

@SpringBootTest
public class AssociadoServiceTest {

	private static final Long ID = 1L;
	private static final String CPF = "48101654003";
	private static final String NOME = "FULANO";

	@InjectMocks
	private AssociadoServiceImpl associadoServiceImpl;

	@MockBean
	private AssociadoRepository associadoRepository;

	private AssociadoRequest associadoRequest;
	private AssociadoResponse associadoResponse;

	private Optional<AssociadoEntity> optionalAssociado;
	private AssociadoEntity associadoEntity;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		start();
	}

	@Test
	void deveRetornarSucesso() throws Exception {

		Mockito.when(associadoRepository.save(Mockito.any())).thenReturn(associadoEntity);

		AssociadoResponse response = associadoServiceImpl.insert(associadoRequest);
		
		Assertions.assertNotNull(response);
		Assertions.assertEquals(AssociadoResponse.class, response.getClass());

	}

	@Test
	void deveRetornarAssociadoJaCadastradoExceptionQuandoPesquisarPorCpfEUmAssociadoForEncontrado() throws Exception {

		Mockito.when(associadoRepository.findByCpf(Mockito.anyString())).thenReturn(associadoEntity);

		try {
			associadoServiceImpl.insert(associadoRequest);
		} catch (Exception e) {
			Assertions.assertEquals(AssociadoJaCadastradoException.class, e.getClass());
			Assertions.assertEquals("Associado já cadastrado", e.getMessage());
		}

	}

	@Test
	void deveRetornarAssociadoResponseQuandoPesquisarPorId() throws AssociadoNotFoundException {

		Mockito.when(associadoRepository.findById(Mockito.anyLong())).thenReturn(optionalAssociado);

		AssociadoResponse response = associadoServiceImpl.findById(ID);

		Assertions.assertNotNull(response);
		Assertions.assertEquals(AssociadoResponse.class, response.getClass());
		Assertions.assertEquals(ID, response.getId());
		Assertions.assertEquals(CPF, response.getCpf());
		Assertions.assertEquals(NOME, response.getNome());

	}

	@Test
	void deveRetornarAssociadoNotFoundExceptionQuandoAssociadoNaoEncontradoPeloId() {

		Mockito.when(associadoRepository.findById(Mockito.anyLong()))
				.thenThrow(new AssociadoNotFoundException("Associado não localizado"));

		try {
			associadoServiceImpl.findById(ID);
		} catch (Exception e) {
			Assertions.assertEquals(AssociadoNotFoundException.class, e.getClass());
			Assertions.assertEquals("Associado não localizado", e.getMessage());
		}

	}

	@Test
	void deveRetornarAssociadoResponseQuandoPesquisarPorCpf() throws AssociadoNotFoundException {

		Mockito.when(associadoRepository.findByCpf(Mockito.anyString())).thenReturn(associadoEntity);

		AssociadoResponse response = associadoServiceImpl.findByCpf(CPF);

		Assertions.assertNotNull(response);
		Assertions.assertEquals(AssociadoResponse.class, response.getClass());
		Assertions.assertEquals(ID, response.getId());
		Assertions.assertEquals(CPF, response.getCpf());
		Assertions.assertEquals(NOME, response.getNome());
	}

	@Test
	void deveRetornarAssociadoNotFoundExceptionQuandoAssociadoNaoEncontradoPeloCpf() {

		Mockito.when(associadoRepository.findByCpf(Mockito.anyString()))
				.thenThrow(new AssociadoNotFoundException("Associado não localizado"));

		try {
			associadoServiceImpl.findByCpf(CPF);
		} catch (Exception e) {
			Assertions.assertEquals(AssociadoNotFoundException.class, e.getClass());
			Assertions.assertEquals("Associado não localizado", e.getMessage());

		}

	}

	private void start() {
		associadoRequest = new AssociadoRequest(CPF, NOME);

		associadoEntity = new AssociadoEntity(ID, CPF, NOME);
		optionalAssociado = Optional.of(associadoEntity);

		associadoResponse = new AssociadoResponse(ID, CPF, NOME);

	}

}
