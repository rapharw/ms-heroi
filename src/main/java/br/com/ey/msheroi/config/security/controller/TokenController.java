package br.com.ey.msheroi.config.security.controller;

import br.com.ey.msheroi.controllers.CommonController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
public class TokenController extends CommonController {

    @GetMapping
    public ResponseEntity validateToken(){
        return ok();
    }
}
