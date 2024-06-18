package com.estagio.Api_Banco.services.exceptions;

public class MalformedJsonException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public MalformedJsonException(String msg) {
        super(msg);
    }
}