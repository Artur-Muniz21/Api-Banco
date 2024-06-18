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

import com.estagio.Api_Banco.dto.GetTransferencia;
import com.estagio.Api_Banco.dto.PostTransferencia;
import com.estagio.Api_Banco.dto.response.TransferenciaResponse;
import com.estagio.Api_Banco.entities.Conta;
import com.estagio.Api_Banco.entities.Transferencia;
import com.estagio.Api_Banco.services.ContaService;
import com.estagio.Api_Banco.services.EmailSendService;
import com.estagio.Api_Banco.services.TransferenciaService;

@RestController
@RequestMapping(value = "/transferencia")
public class TransferenciaResource {

	@Autowired
	private TransferenciaService service;
	
	@Autowired
	private ContaService contaService;
	
	@Autowired
	private EmailSendService emailSendService;
	
	@GetMapping
	public ResponseEntity<List<GetTransferencia>> findAll(){
		
		List<GetTransferencia> list = service.findAll().stream()
				.map(GetTransferencia :: new)
				.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetTransferencia> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(new GetTransferencia(service.findById(id)));
	}
	
	@PostMapping
	public ResponseEntity<TransferenciaResponse> insert(@RequestBody PostTransferencia postTransferencia){
		Conta conta = contaService.findById(postTransferencia.getIdConta().getId());
		
		//Transferencia(String agenciaDestinatario, String nrContaDestinatario, String status, Date data, BigDecimal valor, Conta conta)
		Transferencia transferencia = new Transferencia(postTransferencia.getAgenciaDestinatario(), postTransferencia.getNrContaDestinatario(), postTransferencia.getValor(), conta);
		transferencia = service.insert(transferencia);
		TransferenciaResponse transferenciaResponse = new TransferenciaResponse(transferencia);
		
		emailSendService.sendEmail(transferencia.getAgenciaDestinatario(), transferencia.getNrContaDestinatario());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(transferenciaResponse);
	}
}
