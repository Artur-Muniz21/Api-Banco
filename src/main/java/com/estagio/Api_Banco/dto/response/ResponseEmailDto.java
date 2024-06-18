package com.estagio.Api_Banco.dto.response;

public class ResponseEmailDto {
	private String resultado;
	
	public ResponseEmailDto() {
		
	}
	
	public ResponseEmailDto(String resultado) {
		this.resultado = resultado;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
}
