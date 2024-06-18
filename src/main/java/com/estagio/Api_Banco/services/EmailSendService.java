package com.estagio.Api_Banco.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estagio.Api_Banco.email.EmailSend;
import com.estagio.Api_Banco.entities.Conta;
import com.estagio.Api_Banco.repositories.ContaRepository;
import com.estagio.Api_Banco.services.exceptions.EmailSendException;

@Service
public class EmailSendService {

	@Autowired
	public EmailSend emailSend;
	
	@Autowired
	public ContaRepository contaRepository;
	
	public void sendEmail(Conta destinatario) {
		
		String emailDestinatario = destinatario.getUsuario().getEmail();
		
		try {
			emailSend.sendEmail(emailDestinatario);
			
		}catch (Exception e) {
			throw new EmailSendException(e.getMessage());
			
		}
	}
}
