package com.menete.ORDEM_SERVICO.domain.exceptions;

public class objectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public objectNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public objectNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public objectNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public objectNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public objectNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
