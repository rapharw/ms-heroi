package br.com.ey.msheroi.config.security.controller;

import br.com.ey.msheroi.controllers.CommonController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController extends CommonController {

    @GetMapping()
    public ResponseEntity usuarioLogado(){
        return ok(userPrincipal());
    }
}
