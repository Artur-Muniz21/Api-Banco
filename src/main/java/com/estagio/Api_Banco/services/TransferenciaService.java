package com.estagio.Api_Banco.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estagio.Api_Banco.entities.Conta;
import com.estagio.Api_Banco.entities.Transferencia;
import com.estagio.Api_Banco.entities.enums.TipoUsuario;
import com.estagio.Api_Banco.repositories.ContaRepository;
import com.estagio.Api_Banco.repositories.TransferencioaRepository;
import com.estagio.Api_Banco.services.exceptions.DatabaseException;
import com.estagio.Api_Banco.services.exceptions.ResourceNotFound;

@Service
public class TransferenciaService {

	@Autowired
	private TransferencioaRepository repository;

	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private EmailSendService emailSendService;

	public List<Transferencia> findAll() {
		return repository.findAll();
	}

	public Transferencia findById(Long id) {
		return repository.findById(id).orElseThrow();
	}

	public Transferencia insert(Transferencia transferencia) {

		Long contaId = transferencia.getConta().getId();

		if (!contaRepository.existsById(contaId)) {
			throw new ResourceNotFound(contaId);
		}

		if (!contaRepository.existsByAgenciaAndNrConta(transferencia.getAgenciaDestinatario(), transferencia.getNrContaDestinatario())) {
			throw new DatabaseException("Informações do destinatário incorretas!");
		}

		if (transferencia.getConta().getSaldo().subtract(transferencia.getValor()).compareTo(BigDecimal.ZERO) == -1) {
			transferencia.setStatus("Cancelada: Saldo insuficiente");
			return repository.save(transferencia);
		}
		
		if (transferencia.getConta().getUsuario().getTipo() == TipoUsuario.LOJISTA) {
			throw new DatabaseException("Lojista não realiza transferencia");
		}
		
		Conta destinatario = contaRepository.findByAgenciaAndNrConta(transferencia.getAgenciaDestinatario(), transferencia.getNrContaDestinatario());
		
		emailSendService.sendEmail(destinatario);
		
		 BigDecimal novoSaldo = transferencia.getConta().getSaldo().subtract(transferencia.getValor());
		 transferencia.getConta().setSaldo(novoSaldo);
		 destinatario.setSaldo(destinatario.getSaldo().add(transferencia.getValor()));
		 transferencia.setStatus("Realizada"); 

		// List<Conta> contaDestinatario =
		// contaRepository.findByAgencia(AgenciaDestinatario);
		/*
		 * for(Conta conta: contaDestinatario) {
		 * if(conta.getNrConta().equals(nrContaDestinatario)) { BigDecimal novoSaldo =
		 * transferencia.getConta().getSaldo().subtract(transferencia.getValor());
		 * transferencia.getConta().setSaldo(novoSaldo);
		 * conta.setSaldo(conta.getSaldo().add(transferencia.getValor()));
		 * transferencia.setStatus("Realizada"); break; } }
		 */

		return repository.save(transferencia);

	}
}
