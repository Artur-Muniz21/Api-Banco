package com.estagio.Api_Banco.services.exceptions;

public class ResourceNotFound extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFound(Object id) { 
		super("Resource not found. Id " + id);
	}

}
