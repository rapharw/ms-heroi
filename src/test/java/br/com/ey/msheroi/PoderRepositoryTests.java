package br.com.ey.msheroi;

import br.com.ey.msheroi.repository.PoderRepository;
import br.com.ey.msheroi.vo.Poder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@SpringBootTest
@ActiveProfiles("dsv")
@Slf4j
public class PoderRepositoryTests {

	public static final Integer ID_PODER_PALACIO_DA_MEMORIA = 1;
	public static final String DESCRICAO_PODER_PALACIO_DA_MEMORIA_EXPECTED = "Palácio da Memória";

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
