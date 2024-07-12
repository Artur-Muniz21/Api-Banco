package com.estagio.Api_Banco.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.estagio.Api_Banco.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario>{

	public boolean existsByEmail(String email);
	
	public boolean existsByCpf(String cpf);
}
