package br.com.ey.msheroi.repository;

import br.com.ey.msheroi.vo.Universo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static br.com.ey.msheroi.utils.ConstantesTests.DESCRICAO_EY_COMICS_EXPECTED;
import static br.com.ey.msheroi.utils.ConstantesTests.ID_EY_COMICS;

@SpringBootTest
@ActiveProfiles("dsv")
@Slf4j
public class UniversosRepositoryTests {

	@Autowired
	private UniversoRepository universoRepository;

	@Test
	public void findAtLeastOneUniverso() {
		Optional<Universo> byId = universoRepository.findById(ID_EY_COMICS);

		if(byId.isPresent()){
			Universo universo = byId.get();
			log.info("Universo: {}", universo);
			Assertions.assertEquals(DESCRICAO_EY_COMICS_EXPECTED, universo.getDescricao());
		}
		else{
			log.info("Universo não encontrado");
			Assertions.fail();
		}
	}

}
