package com.estagio.Api_Banco.dto;

import java.math.BigDecimal;

import com.estagio.Api_Banco.entities.Conta;

public class GetConta {

	private Long id;
	private String agencia;
	private String nrConta;
	private BigDecimal saldo;
	
	public GetConta() {
		
	}
	
	public GetConta(Conta conta) {
		this.id = conta.getId();
		this.agencia = conta.getAgencia();
		this.nrConta = conta.getNrConta();
		this.saldo = conta.getSaldo();
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
}
