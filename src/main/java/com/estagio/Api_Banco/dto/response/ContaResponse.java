package com.estagio.Api_Banco.dto.response;

import java.math.BigDecimal;

import com.estagio.Api_Banco.entities.Conta;

public class ContaResponse {
	private Long id;
	private String agencia;
	private String nrConta;
	private BigDecimal saldo;
	private String nomeUsuario;
	
	public ContaResponse() {
		
	}
	
	public ContaResponse(Conta conta) {
		this.id = conta.getId();
		this.agencia = conta.getAgencia();
		this.nrConta = conta.getNrConta();
		this.saldo = conta.getSaldo();
		this.nomeUsuario = conta.getUsuario().getNomeCompleto();
	}

	public Long getId() {
		return id;
	}

	public String getAgencia() {
		return agencia;
	}

	public String getNrConta() {
		return nrConta;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}	
}
