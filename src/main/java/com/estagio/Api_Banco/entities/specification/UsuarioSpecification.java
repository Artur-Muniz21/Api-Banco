package com.estagio.Api_Banco.entities.specification;

import org.springframework.data.jpa.domain.Specification;

import com.estagio.Api_Banco.entities.Usuario;
import com.estagio.Api_Banco.entities.enums.TipoUsuario;

public class UsuarioSpecification {

    public static Specification<Usuario> tipoUsuarioIgual(TipoUsuario tipoUsuario) {
        return (root, query, criteriaBuilder) -> 
            criteriaBuilder.equal(root.get("tipo"), tipoUsuario);
    }
		
	
}
