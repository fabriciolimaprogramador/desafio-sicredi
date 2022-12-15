package br.com.fabricio.cooperativismo.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.fabricio.cooperativismo.util.DataHoraUtil;

@RestControllerAdvice
public class ApiExceptions {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> tratarException(Exception e) {
		return ResponseEntity.badRequest()
				.body(new ErroDetectado(DataHoraUtil.format(LocalDateTime.now()), e.getMessage()));
	}
	
	@ExceptionHandler(AssociadoNotFoundException.class)
	public ResponseEntity<?> tratarAssociadoNotFound(AssociadoNotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroDetectado(DataHoraUtil.format(LocalDateTime.now()), e.getMessage()));
	}
	
	@ExceptionHandler(CpfAssociadoInvalidoException.class)
	public ResponseEntity<?> tratarCpfAssociadoInvalido(CpfAssociadoInvalidoException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroDetectado(DataHoraUtil.format(LocalDateTime.now()), e.getMessage()));
	}
}
