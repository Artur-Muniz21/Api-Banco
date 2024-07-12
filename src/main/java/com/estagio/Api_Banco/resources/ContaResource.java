package com.estagio.Api_Banco.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estagio.Api_Banco.dto.GetConta;
import com.estagio.Api_Banco.dto.PostConta;
import com.estagio.Api_Banco.dto.response.ContaResponse;
import com.estagio.Api_Banco.entities.Conta;
import com.estagio.Api_Banco.entities.Usuario;
import com.estagio.Api_Banco.services.ContaService;
import com.estagio.Api_Banco.services.UsuarioService;

@RestController
@RequestMapping(value = "/contas")
public class ContaResource {

    @Autowired
    private ContaService service;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<GetConta>> findAll() {
        List<GetConta> list = service.findAll().stream()
                .map(GetConta::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("{id}")
    public ResponseEntity<GetConta> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(new GetConta(service.findById(id)));
    }

    @PostMapping
    public ResponseEntity<ContaResponse> insert(@RequestBody PostConta postConta) {
        Usuario usuario = usuarioService.findById(postConta.getIdUsuario().getId());
        Conta conta = new Conta(postConta.getAgencia(), null, usuario);
        conta = service.insert(conta);
        ContaResponse contaResponse = new ContaResponse(conta);
        return ResponseEntity.status(HttpStatus.CREATED).body(contaResponse);
    }
}
