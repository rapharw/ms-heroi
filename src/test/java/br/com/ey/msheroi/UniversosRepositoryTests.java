package br.com.ey.msheroi;

import br.com.ey.msheroi.enums.TipoSituacaoEnum;
import br.com.ey.msheroi.enums.TipoUniversoEnum;
import br.com.ey.msheroi.repository.UniversoRepository;
import br.com.ey.msheroi.vo.Heroi;
import br.com.ey.msheroi.vo.Universo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("dsv")
@Slf4j
public class UniversosRepositoryTests {

	private static final Integer ID_EY_COMICS = TipoUniversoEnum.EY_COMICS.getId();
	private static final String DESCRICAO_EY_COMICS_EXPECTED = "EY Comics";

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
