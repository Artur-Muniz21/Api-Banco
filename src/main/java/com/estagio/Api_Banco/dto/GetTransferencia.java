package com.estagio.Api_Banco.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.estagio.Api_Banco.entities.Transferencia;

public class GetTransferencia {

	private String AgenciaDestinatario;
	private String nrContaDestinatario;
	private String status;
	private Date data;
	private BigDecimal valor;
	
	public GetTransferencia() {
		
	}
	
	public GetTransferencia(Transferencia transferencia) {
		this.AgenciaDestinatario = transferencia.getAgenciaDestinatario();
		this.nrContaDestinatario = transferencia.getNrContaDestinatario();
		this.status = transferencia.getStatus();
		this.data = transferencia.getData();
		this.valor = transferencia.getValor();
	}

	public String getAgenciaDestinatario() {
		return AgenciaDestinatario;
	}

	public String getNrContaDestinatario() {
		return nrContaDestinatario;
	}

	public String getStatus() {
		return status;
	}

	public Date getData() {
		return data;
	}

	public BigDecimal getValor() {
		return valor;
	}
}
