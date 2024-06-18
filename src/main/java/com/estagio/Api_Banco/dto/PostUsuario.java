package com.estagio.Api_Banco.dto;

import com.estagio.Api_Banco.entities.Usuario;
import com.estagio.Api_Banco.entities.enums.TipoUsuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class PostUsuario {

	@NotBlank(message = "Email obrigatório")
	private String email;
	
	@NotBlank(message = "Senha obrigatória")
	private String senha;
	
	@NotBlank(message = "Nome obrigatório")
	private String nomeCompleto;
	
	private String celular;
	
	@NotBlank(message = "CPF obrigatório")
	private String cpf;
	
	@NotNull(message = "Tipo de usuário obrigatório")
	private TipoUsuario tipo;
	
	public PostUsuario() {
		
	}
	
	public PostUsuario(String email, String senha, String nomeCompleto, String celular, String cpf, TipoUsuario tipo) {
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

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}
	
	public Usuario toUsuario() {
		return new Usuario(this.email, this.senha, this.nomeCompleto, this.celular, this.cpf, this.tipo);
	}
}
