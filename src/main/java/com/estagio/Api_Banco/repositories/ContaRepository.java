package com.estagio.Api_Banco.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estagio.Api_Banco.entities.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>{

	public List<Conta> findByAgencia(String Agencia);
}
