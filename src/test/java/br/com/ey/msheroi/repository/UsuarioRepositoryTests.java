package br.com.ey.msheroi.repository;

import br.com.ey.msheroi.vo.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static br.com.ey.msheroi.utils.ConstantesTests.*;

@SpringBootTest
@ActiveProfiles("dsv")
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioRepositoryTests {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Test
	@Order(1)
	public void findUsuarioRaphael() {
		Optional<Usuario> byId = usuarioRepository.findById(ID_USUARIO_RAPHAEL);
		if(byId.isPresent()) {
			log.info("Usuario: {}", byId.get().getNome());
			log.info("Login: {}", byId.get().getUsername());
			Assertions.assertEquals(NOME_USUARIO_RAPHAEL, byId.get().getNome());
			Assertions.assertEquals(LOGIN_USUARIO_RAPHAEL, byId.get().getUsername());
		}
		else
			Assertions.fail();
	}





	@Test
	@Order(2)
	public void findAllUsuarios() {
		List<Usuario> all = usuarioRepository.findAll();

		if(!all.isEmpty()){
			all.forEach(usuario -> log.info("ID - Nome do Usuario - Login: {} | {} | {}", usuario.getId(), usuario.getNome(), usuario.getUsername()));
			Assertions.assertTrue(all.size() == 3);
		}
		else{
			Assertions.fail();
		}
	}



}
