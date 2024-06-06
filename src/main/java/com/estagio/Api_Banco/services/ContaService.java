package com.estagio.Api_Banco.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.estagio.Api_Banco.entities.Conta;
import com.estagio.Api_Banco.entities.Usuario;
import com.estagio.Api_Banco.repositories.ContaRepository;
import com.estagio.Api_Banco.repositories.UsuarioRepository;
import com.estagio.Api_Banco.services.exceptions.DatabaseException;
import com.estagio.Api_Banco.services.exceptions.ResourceNotFound;

@Service
public class ContaService {

	@Autowired
	private ContaRepository repository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Conta> findAll(){
		return repository.findAll();
	}
	
	public Conta findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFound(id));
	}
	
	public Conta insert(Conta conta) {
		Long usuarioId = conta.getUsuario().getId();
		
		if(!usuarioRepository.existsById(usuarioId)) {
			throw new ResourceNotFound(usuarioId);
		}
			
		//if validar id do usuario
		//if conta 12 na agencia 1
		
		return repository.save(conta);
	}
	
	public Conta update(Long id, Conta conta) {
		
		Conta contaReference = repository.getReferenceById(id);
		
		if(conta.getAgencia() != null) {
			contaReference.setAgencia(conta.getAgencia());
		}
		
		if(conta.getNrConta() != null) {
			contaReference.setNrConta(conta.getNrConta());
			
		}
		
		return repository.save(conta);
	}
	
	public void delete(Long id) {
		try {

			repository.findById(id).orElseThrow(() -> new ResourceNotFound(id));
			repository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {

			throw new ResourceNotFound(id);

		} catch (DataIntegrityViolationException e) {

			throw new DatabaseException(e.getMessage());

		}
	}
}
