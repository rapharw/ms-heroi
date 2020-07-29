package br.com.ey.msheroi.config.security.service;

import br.com.ey.msheroi.config.security.vo.UserPrincipal;
import br.com.ey.msheroi.vo.Usuario;

public interface TokenService {

    String generateToken(Usuario usuario);
    UserPrincipal parseToken(String token);
}
