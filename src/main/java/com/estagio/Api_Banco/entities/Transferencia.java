package com.estagio.Api_Banco.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transferencia")
public class Transferencia implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String AgenciaDestinatario;
	private String nrContaDestinatario;
	private String status;
	private Date data;
	private BigDecimal valor;
	
	@ManyToOne
	@JoinColumn(name = "conta_id")
	private Conta conta;
	
	public Transferencia() {
		
	}
	
	public Transferencia(String agenciaDestinatario, String nrContaDestinatario, Date data,
			BigDecimal valor, Conta conta) {
		AgenciaDestinatario = agenciaDestinatario;
		this.nrContaDestinatario = nrContaDestinatario;
		this.data = new Date();
		this.valor = valor;
		this.conta = conta;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getAgenciaDestinatario() {
		return AgenciaDestinatario;
	}

	public void setAgenciaDestinatario(String agenciaDestinatario) {
		AgenciaDestinatario = agenciaDestinatario;
	}

	public String getNrContaDestinatario() {
		return nrContaDestinatario;
	}

	public void setNrContaDestinatario(String nrContaDestinatario) {
		this.nrContaDestinatario = nrContaDestinatario;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transferencia other = (Transferencia) obj;
		return Objects.equals(id, other.id);
	}

}
