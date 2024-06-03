package com.estagio.Api_Banco.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estagio.Api_Banco.entities.Transferencia;

@Repository
public interface TransferencioaRepository extends JpaRepository<Transferencia, Long>{

}
