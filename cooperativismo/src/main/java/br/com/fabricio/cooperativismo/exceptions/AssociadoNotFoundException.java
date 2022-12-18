package br.com.fabricio.cooperativismo.exceptions;

public class AssociadoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -4768268985244923157L;

	public AssociadoNotFoundException(String message) {
		super(message);
	}

}
