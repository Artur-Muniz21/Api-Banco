package com.estagio.Api_Banco.services.exceptions;

public class EmailSendException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public EmailSendException(String msg) {
		super("Erro ao envioar o email: " + msg);
	}
}
