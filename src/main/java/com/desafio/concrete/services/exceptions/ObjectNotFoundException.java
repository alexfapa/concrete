package com.desafio.concrete.services.exceptions;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String msg) {		
		super("Não encontrado");
	}
	
	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg);
		
	}
}
