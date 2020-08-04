package br.com.ey.msheroi.config.security.controller;

import br.com.ey.msheroi.config.security.service.AutenticacaoService;
import br.com.ey.msheroi.common.vo.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @PostMapping
    public ResponseEntity login(@RequestBody Login login){

        String token = autenticacaoService.realizaAutenticacao(login);

        HttpHeaders headers = new HttpHeaders();
        headers.add("token", token);
        return new ResponseEntity<>("SUCESSO", headers, HttpStatus.OK);
    }
}
