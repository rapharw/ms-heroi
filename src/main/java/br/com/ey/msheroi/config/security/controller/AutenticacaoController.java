package br.com.ey.msheroi.config.security.controller;

import br.com.ey.msheroi.common.request.LoginRequest;
import br.com.ey.msheroi.common.vo.Token;
import br.com.ey.msheroi.config.security.service.AutenticacaoService;
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
    public ResponseEntity<Token> login(@RequestBody LoginRequest loginRequest){

        String tokenAuth = autenticacaoService.realizaAutenticacao(loginRequest.getLoginInstance());

        HttpHeaders headers = new HttpHeaders();
        headers.add("token", tokenAuth);



        return new ResponseEntity<>(Token.builder()
                                            .value(tokenAuth)
                                        .build(), headers, HttpStatus.CREATED);

    }
}
