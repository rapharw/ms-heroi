package br.com.ey.msheroi.repository;

import br.com.ey.msheroi.common.vo.Poder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static br.com.ey.msheroi.utils.ConstantesTests.DESCRICAO_PODER_PALACIO_DA_MEMORIA_EXPECTED;
import static br.com.ey.msheroi.utils.ConstantesTests.ID_PODER_PALACIO_DA_MEMORIA;

@SpringBootTest
@ActiveProfiles("dsv")
@Slf4j
public class PoderRepositoryTests {

	@Autowired
	private PoderRepository poderRepository;

	@Test
	public void findAtLeastOnePoder() {
		Optional<Poder> byId = poderRepository.findById(ID_PODER_PALACIO_DA_MEMORIA);

		if(byId.isPresent()){
			Poder poder = byId.get();
			log.info("Poder: {}", poder.getDescricao());
			log.info("Poder toString: {}", poder);
			Assertions.assertEquals(DESCRICAO_PODER_PALACIO_DA_MEMORIA_EXPECTED, poder.getDescricao());
		}
		else{
			log.info("Poder não encontrado");
			Assertions.fail();
		}
	}

}
