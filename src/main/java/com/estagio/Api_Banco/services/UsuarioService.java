package com.estagio.Api_Banco.services;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.estagio.Api_Banco.entities.Usuario;
import com.estagio.Api_Banco.entities.enums.TipoUsuario;
import com.estagio.Api_Banco.entities.specification.UsuarioSpecification;
import com.estagio.Api_Banco.repositories.UsuarioRepository;
import com.estagio.Api_Banco.services.exceptions.DatabaseException;
import com.estagio.Api_Banco.services.exceptions.ResourceNotFound;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private PasswordEncoder encoder;

	public List<Usuario> findAll(TipoUsuario tipoUsuario) {
	    Specification<Usuario> specification = Specification.where(null);
	    
	    if (Objects.nonNull(tipoUsuario)) {
	        specification = specification.and(UsuarioSpecification.tipoUsuarioIgual(tipoUsuario));
	    }
	    
	    return repository.findAll(specification);
	}

	public Usuario findById(Long id) {

		Usuario user = repository.findById(id).orElseThrow(() -> new ResourceNotFound(id));

		return user;
	}

	public Usuario insert(Usuario user) {

		if (repository.existsByEmail(user.getEmail())) {
			throw new DatabaseException("Email já cadastrado");
		}
		
		if (repository.existsByCpf(user.getCpf())) {
			throw new DatabaseException("CPF já cadastrado");
		}

		String encodePassword = encoder.encode(user.getSenha());
		user.setSenha(encodePassword);

		return repository.save(user);
	}

	public void delete(Long id) {

		try {

			repository.findById(id).orElseThrow(() -> new ResourceNotFound(id));
			repository.deleteById(id);

			// resultado passado for vazio
		} catch (EmptyResultDataAccessException e) {

			throw new ResourceNotFound(id);

			// tentar excluir um registro que tenha relacionamento
		} catch (DataIntegrityViolationException e) {

			throw new DatabaseException(e.getMessage());

		}
	}

	public Usuario update(Long id, Usuario user) {
		
	
		repository.findById(id).orElseThrow(() -> new ResourceNotFound(id));
			
		//obj monitorado
		Usuario userReference = repository.getReferenceById(id);
			
		if(user.getNomeCompleto() != null) {
			userReference.setNomeCompleto(user.getNomeCompleto());
		}
			
		if(user.getEmail() != null) {		
			if(repository.existsByEmail(user.getEmail())) {
				if(user.getEmail().equals(userReference.getEmail())) {
					throw new DatabaseException("Email igual ao anterior");
				}
				throw new DatabaseException("Email já cadastrado");
			}
			
			userReference.setEmail(user.getEmail());
		}
		
		if(user.getCpf() != null) {
			
			if(repository.existsByCpf(user.getCpf())) {
				throw new DatabaseException("CPF já cadastrado");
			}
			
			userReference.setCpf(user.getCpf());
		}
		
		if(user.getTipo() != null) {
			userReference.setTipo(user.getTipo());
		}
		
		if(user.getCelular() != null) {
			userReference.setCelular(user.getCelular());
		}
		
		return repository.save(userReference);
	}

}
