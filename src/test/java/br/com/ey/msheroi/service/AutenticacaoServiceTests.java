package br.com.ey.msheroi.service;

import br.com.ey.msheroi.config.security.enums.PerfilUsuarioEnum;
import br.com.ey.msheroi.config.security.service.TokenService;
import br.com.ey.msheroi.config.security.vo.UserPrincipal;
import br.com.ey.msheroi.vo.Login;
import br.com.ey.msheroi.vo.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static br.com.ey.msheroi.utils.ConstantesTests.*;

@SpringBootTest
@ActiveProfiles("dsv")
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AutenticacaoServiceTests {

    @Autowired
    private TokenService tokenService;

    private static String generateJWT(TokenService tokenService){
        Usuario usuario = Usuario.builder()
                .id(ID_USUARIO_RAPHAEL)
                .login(Login.builder().username(LOGIN_USUARIO_RAPHAEL).build())
                .perfilUsuario(PerfilUsuarioEnum.ROLE_COMUM)
                .build();

        return tokenService.generateToken(usuario);
    }

    @Test
    @Order(1)
    public void generateJwtToken() {

        String jwt = generateJWT(tokenService);

        log.info("Generated token jwt: {}", jwt);
        Assertions.assertNotNull(jwt);
    }

    @Test
    @Order(2)
    public void parseToken(){
        String jwt = generateJWT(tokenService);
        log.info("Generated token jwt: {}", jwt);

        UserPrincipal userPrincipal = tokenService.parseToken(jwt);
        log.info("UserPrincipal: {}", userPrincipal);
        Assertions.assertEquals(LOGIN_USUARIO_RAPHAEL, userPrincipal.getUsername());
    }

}
