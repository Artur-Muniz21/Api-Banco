package com.estagio.Api_Banco.dto;

import com.estagio.Api_Banco.entities.Usuario;

public class GetUsuarios {

	private Long id;
	private String email;
	private String nomeCompleto;
	private String celular;
	private String cpf;
	private String tipo;
	
	public GetUsuarios() {
		
	}

	public GetUsuarios(Usuario user) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.nomeCompleto = user.getNomeCompleto();
		this.celular = user.getCelular();
		this.cpf = user.getCpf();
		this.tipo = user.getTipo();
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}


	public String getCelular() {
		return celular;
	}

	public String getCpf() {
		return cpf;
	}

	public String getTipo() {
		return tipo;
	}
}
