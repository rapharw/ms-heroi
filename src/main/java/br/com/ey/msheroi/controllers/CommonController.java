package br.com.ey.msheroi.controllers;

import br.com.ey.msheroi.config.security.vo.UserPrincipal;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;

public class CommonController {

    public static final String ROTA_API = "/api";

    public ResponseEntity ok(){
        return ok(null, null);
    }

    public ResponseEntity ok(Object obj){
        return ok(obj, "Operacao realizada com sucesso");
    }

    public ResponseEntity ok(String msg){
        return ok(null, msg);
    }

    public ResponseEntity ok(Object obj, String msg){
        HttpHeaders headers = new HttpHeaders();

        if(obj != null && msg != null){
            headers.add("message", msg);
            return new ResponseEntity<>(obj, headers, HttpStatus.OK);
        }
        else if (msg != null) {
            headers.add("message", msg);
            return new ResponseEntity<>(headers, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity error(String msg){
        return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
    }

    public UserPrincipal userPrincipal(){
        return (UserPrincipal) SecurityContextHolder.getContext()
                                                    .getAuthentication()
                                                    .getPrincipal();
    }
}
