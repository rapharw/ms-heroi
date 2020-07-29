package br.com.ey.msheroi.config.security.provider;

import br.com.ey.msheroi.repository.LoginRepository;
import br.com.ey.msheroi.repository.UsuarioRepository;
import br.com.ey.msheroi.vo.Login;
import br.com.ey.msheroi.vo.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DatabaseAuthProvider {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario authenticate(Login login){
        log.info("... Logando usuario {} ...", login.getUsername());

        Login byUsername = loginRepository.findByUsername(login.getUsername());

        if(byUsername != null && byUsername.checkPassword(login.getPassword())){
            log.info("... {} AUTENTICADO COM SUCESSO ...", login.getUsername());
            return byUsername.getUsuario();
        }
        log.info("... {} NAO ENCONTRADO ...", login.getUsername());
        return null;
    }
}
