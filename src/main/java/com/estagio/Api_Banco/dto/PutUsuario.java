package com.estagio.Api_Banco.dto;

import com.estagio.Api_Banco.entities.Usuario;

public class PutUsuario {

	private String email;
	private String senha;
	private String nomeCompleto;
	private String celular;
	private String cpf;
	private String tipo;
	
	public PutUsuario() {
		
	}
	
	public PutUsuario(String email, String senha, String nomeCompleto, String celular, String cpf, String tipo) {
		this.email = email;
		this.senha = senha;
		this.nomeCompleto = nomeCompleto;
		this.celular = celular;
		this.cpf = cpf;
		this.tipo = tipo;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public Usuario toUsuario() {
		return new Usuario(this.email, this.senha, this.nomeCompleto, this.celular, this.cpf, this.tipo);
	}
}
