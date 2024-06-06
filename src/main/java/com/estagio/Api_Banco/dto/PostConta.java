package com.estagio.Api_Banco.dto;

public class PostConta {

	private String agencia;
	private GetIdUsuario idUsuario; //fazer dto
	
	public PostConta() {
		
	}

	public PostConta(String agencia, GetIdUsuario idUsuario) {
		this.agencia = agencia;
		this.idUsuario = idUsuario;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public void setIdUsuario(GetIdUsuario idUsuario) {
		this.idUsuario = idUsuario;
	}

	public GetIdUsuario getIdUsuario() {
		return idUsuario;
	}
}
