package com.estagio.Api_Banco.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.estagio.Api_Banco.entities.Conta;
import com.estagio.Api_Banco.entities.Transferencia;
import com.estagio.Api_Banco.entities.Usuario;
import com.estagio.Api_Banco.repositories.ContaRepository;
import com.estagio.Api_Banco.repositories.TransferencioaRepository;
import com.estagio.Api_Banco.repositories.UsuarioRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private TransferencioaRepository transferenciaRepository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
        Usuario user1 = new Usuario("tuka@gmail.com", "12345", "Artur Muniz", "998847867", "1234576788", "comum");
        usuarioRepository.save(user1);
        
        Conta conta1 = new Conta("38483", "123546", 694948989.34, user1); 
        contaRepository.save(conta1);
        
        Transferencia tranf1 = new Transferencia("Pedro", "Realizada", new Date(), conta1);
        transferenciaRepository.save(tranf1);
	}

}
