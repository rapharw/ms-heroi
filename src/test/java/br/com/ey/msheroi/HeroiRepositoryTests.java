package br.com.ey.msheroi;

import br.com.ey.msheroi.enums.TipoSituacaoEnum;
import br.com.ey.msheroi.enums.TipoUniversoEnum;
import br.com.ey.msheroi.repository.HeroiRepository;
import br.com.ey.msheroi.vo.Heroi;
import br.com.ey.msheroi.vo.Poder;
import br.com.ey.msheroi.vo.Universo;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static br.com.ey.msheroi.PoderRepositoryTests.ID_PODER_PALACIO_DA_MEMORIA;

@SpringBootTest
@ActiveProfiles("dsv")
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HeroiRepositoryTests {

	private static final String NOME_HEROI = "Sherlock H.";
	private static final Integer ID_UNIVERSO_HEROI = TipoUniversoEnum.EY_COMICS.getId();
	private static final Long ID_HEROI_CRIADO = 1L;

	@Autowired
	private HeroiRepository heroiRepository;

	private static Heroi newHeroInstance(int i){

		//Cria uma instancia de um heroi
		Heroi heroi = Heroi.builder()
				.nome("Herói de num - " + i)
				.situacao(TipoSituacaoEnum.ATIVO)
				.universo(Universo.builder().id(ID_UNIVERSO_HEROI).build())
				.build();

		//Adiciona um poder p/ o Heroi
		heroi.adicionaUmPoder(Poder.builder()
				.id(ID_PODER_PALACIO_DA_MEMORIA)
				.build());
		return heroi;
	}

	private static Heroi findHeroRepository(HeroiRepository heroiRepository, Long idHeroi){
		Optional<Heroi> byId = heroiRepository.findById(idHeroi);

		if(byId.isPresent()) {
			Heroi heroi = byId.get();
			log.info("Nome do Heroi: {}", heroi.getNome());
			log.info("Universo: {}", heroi.getUniverso().getDescricao());
			heroi.getPoderes().forEach(poder -> log.info("Poder: {}", poder.getDescricao()));
			log.info("Heroi: {}", heroi.toJson(new GsonBuilder()));
			return heroi;
		}
		return null;
	}

	@Test
	@Order(1)
	public void createOneHero(){
		//Cria uma instancia de um heroi
		Heroi heroi = Heroi.builder()
								.nome(NOME_HEROI)
								.situacao(TipoSituacaoEnum.ATIVO)
								.universo(Universo.builder().id(ID_UNIVERSO_HEROI).build())
							.build();

		//Adiciona um poder p/ o Heroi
		heroi.adicionaUmPoder(Poder.builder()
									.id(ID_PODER_PALACIO_DA_MEMORIA)
									.build());

		Heroi created = heroiRepository.save(heroi);
		log.info("Heroi salvo: {}", created.toJson(new GsonBuilder()));

		Assertions.assertNotNull(created);
	}

	@Test
	@Order(2)
	public void findOneHero() {
		Heroi heroi = findHeroRepository(heroiRepository, ID_HEROI_CRIADO);
		if(heroi != null)
			Assertions.assertEquals(NOME_HEROI, heroi.getNome());
		else
			Assertions.fail();
	}


	@Test
	@Order(3)
	public void createThreeHeroes(){
		for(int i = 1; i <= 3; i++){
			Heroi heroi = newHeroInstance(i);

			Heroi created = heroiRepository.save(heroi);
			log.info("Heroi {} salvo: {}", i, created.toJson(new GsonBuilder()));

			heroi = null;
			created = null;
		}
		Assertions.assertTrue(true);
	}


	@Test
	@Order(4)
	public void findAllHeroes() {
		List<Heroi> all = heroiRepository.findAll();

		if(!all.isEmpty()){
			all.forEach(heroi -> log.info("ID - Nome do Heroi: {} | {}", heroi.getId(), heroi.getNome()));
			Assertions.assertTrue(all.size() > 1);
		}
		else{
			Assertions.fail();
		}
	}


	@Test
	@Order(5)
	public void updateHero() {
		Heroi heroi = findHeroRepository(heroiRepository, ID_HEROI_CRIADO);
		if(heroi != null){
			String nomeAlteradoExpected = "Nome alterado";

			heroi.setNome("Nome alterado");
			Heroi heroiUpdated = heroiRepository.save(heroi);

			Assertions.assertEquals(nomeAlteradoExpected, heroiUpdated.getNome());
		}
		else{
			Assertions.fail();
		}
	}

	@Test
	@Order(6)
	public void logicDeleteHero() {
		Heroi heroi = findHeroRepository(heroiRepository, ID_HEROI_CRIADO);
		if(heroi != null){
			heroi.setSituacao(TipoSituacaoEnum.INATIVO);
			Heroi heroiUpdated = heroiRepository.save(heroi);
			Assertions.assertEquals(TipoSituacaoEnum.INATIVO, heroiUpdated.getSituacao());
		}
		else{
			Assertions.fail();
		}
	}

	@Test
	@Order(7)
	public void findAllOnlyAtivos() {
		//Heroi inativado no Teste 6
		Heroi heroi = findHeroRepository(heroiRepository, ID_HEROI_CRIADO);

		List<Heroi> allBySituacaoAtivo = heroiRepository.findAllBySituacao(TipoSituacaoEnum.ATIVO);

		Assertions.assertTrue(!allBySituacaoAtivo.contains(heroi));
	}


}
