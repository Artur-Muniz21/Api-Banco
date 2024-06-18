package com.estagio.Api_Banco.email;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.estagio.Api_Banco.dto.response.ResponseEmailDto;

@FeignClient(name = "email", url = "https://v5kqq.wiremockapi.cloud")
public interface EmailSend {

	@PostMapping(value = "/emails")
	ResponseEmailDto sendEmail(@RequestBody String emailDestinatario);
}
