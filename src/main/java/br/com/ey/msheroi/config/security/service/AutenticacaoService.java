package br.com.ey.msheroi.config.security.service;

import br.com.ey.msheroi.config.security.provider.DatabaseAuthProvider;
import br.com.ey.msheroi.exception.UsuarioNaoAutenticadoException;
import br.com.ey.msheroi.vo.Login;
import br.com.ey.msheroi.vo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService {

    @Autowired
    private DatabaseAuthProvider databaseAuthProvider;

    @Autowired
    private TokenService tokenService;

    public String realizaAutenticacao(Login login){
        Usuario userAuthenticated = databaseAuthProvider.authenticate(login);

        if(userAuthenticated != null){
            return tokenService.generateToken(userAuthenticated);
        }
        throw new UsuarioNaoAutenticadoException();
    }
}
