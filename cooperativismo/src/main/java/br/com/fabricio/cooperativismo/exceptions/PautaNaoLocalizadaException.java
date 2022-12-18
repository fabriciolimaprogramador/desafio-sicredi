package br.com.fabricio.cooperativismo.exceptions;

public class PautaNaoLocalizadaException extends RuntimeException {

	private static final long serialVersionUID = 1644740369177413737L;

	public PautaNaoLocalizadaException(String message) {
		super(message);
	}

}
