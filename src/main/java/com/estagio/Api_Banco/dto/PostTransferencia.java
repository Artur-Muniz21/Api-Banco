package com.estagio.Api_Banco.dto;

import java.math.BigDecimal;
import java.util.Date;

public class PostTransferencia {

	private String agenciaDestinatario;
	private String nrContaDestinatario;
	private Date data;
	private BigDecimal valor;
	private GetIdConta idConta;
	
	public PostTransferencia() {
		
	}

	public PostTransferencia(String agenciaDestinatario, String nrContaDestinatario, BigDecimal valor, GetIdConta idConta) {
		this.agenciaDestinatario = agenciaDestinatario;
		this.nrContaDestinatario = nrContaDestinatario;
		this.data = data;
		this.valor = valor;
		this.idConta = idConta;
	}



	public String getAgenciaDestinatario() {
		return agenciaDestinatario;
	}

	public void setAgenciaDestinatario(String agenciaDestinatario) {
		this.agenciaDestinatario = agenciaDestinatario;
	}

	public String getNrContaDestinatario() {
		return nrContaDestinatario;
	}

	public void setNrContaDestinatario(String nrContaDestinatario) {
		this.nrContaDestinatario = nrContaDestinatario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public GetIdConta getIdConta() {
		return idConta;
	}

	public void setIdConta(GetIdConta idConta) {
		this.idConta = idConta;
	}
}
