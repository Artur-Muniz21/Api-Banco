package com.estagio.Api_Banco.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estagio.Api_Banco.dto.GetUsuarios;
import com.estagio.Api_Banco.dto.PostUsuario;
import com.estagio.Api_Banco.dto.PutUsuario;
import com.estagio.Api_Banco.dto.response.UsuarioResponse;
import com.estagio.Api_Banco.entities.Usuario;
import com.estagio.Api_Banco.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/usuarios")
@Validated
public class UsuarioResource {

	@Autowired
	private UsuarioService service;
	
	@GetMapping
	public ResponseEntity<List<GetUsuarios>> findAll(){
		
		List<GetUsuarios> list = service.findAll().stream()
				.map(GetUsuarios::new)
				.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetUsuarios> findById(@PathVariable Long id){
		return ResponseEntity.ok().body(new GetUsuarios(service.findById(id)));
	}
	
	@PostMapping
	public ResponseEntity<UsuarioResponse> insert(@Valid @RequestBody PostUsuario userPost){
		Usuario user = userPost.toUsuario();
		user = service.insert(user);
		UsuarioResponse usuarioReponse = new UsuarioResponse(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioReponse);
	}
	
	@PutMapping(value = "{id}")
	public ResponseEntity<UsuarioResponse> update(@PathVariable Long id, @RequestBody PutUsuario userPut){
		Usuario user = userPut.toUsuario();
		user = service.update(id, user);
		UsuarioResponse usuarioReponse = new UsuarioResponse(user);
		return ResponseEntity.status(HttpStatus.OK).body(usuarioReponse);
	}
}