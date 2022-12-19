package br.com.fabricio.cooperativismo.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import br.com.fabricio.cooperativismo.controller.response.AssociadoResponse;
import br.com.fabricio.cooperativismo.service.AssociadoService;

@SpringBootTest
public class AssociadoControllerTest {

	private static final Long ID = 1L;
	private static final String CPF = "48101654003";
	private static final String NOME = "FULANO";

	@InjectMocks
	private AssociadoController associadoController;

	@Mock
	private AssociadoService associadoService;

	private AssociadoResponse associadoResponse;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		start();

	}

	@Test
	void quandoFindByIdEntaoRetorneSucesso() throws Exception {
		Mockito.when(associadoService.findById(Mockito.anyLong())).thenReturn(associadoResponse);

		ResponseEntity<AssociadoResponse> response = associadoController.findById(ID);

		Assertions.assertNotNull(response);
		Assertions.assertNotNull(response.getBody());
		Assertions.assertEquals(ResponseEntity.class, response.getClass());
		Assertions.assertEquals(AssociadoResponse.class, response.getBody().getClass());

		Assertions.assertEquals(ID, response.getBody().getId());
		Assertions.assertEquals(NOME, response.getBody().getNome());
		Assertions.assertEquals(CPF, response.getBody().getCpf());

	}

	private void start() {
		associadoResponse = new AssociadoResponse(ID, CPF, NOME);

	}

}
