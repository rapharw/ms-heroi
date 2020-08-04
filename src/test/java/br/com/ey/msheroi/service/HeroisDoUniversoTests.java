package br.com.ey.msheroi.service;

import br.com.ey.msheroi.model.heroi.facade.HeroiFacade;
import br.com.ey.msheroi.common.vo.Heroi;
import br.com.ey.msheroi.common.vo.Universo;
import br.com.ey.msheroi.model.heroi.service.HeroisDoUniversoService;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static br.com.ey.msheroi.utils.ConstantesTests.ID_EY_COMICS;
import static br.com.ey.msheroi.utils.ConstantesTests.newHeroInstance;

@SpringBootTest
@ActiveProfiles("dsv")
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class HeroisDoUniversoTests {

    @Autowired
    private HeroisDoUniversoService heroisDoUniversoService;

    @Autowired
    private HeroiFacade heroiFacade;

    @Test
    @Order(1)
    public void buscaOsHeroisDeUmUniverso(){
        Integer heroisASeremCriados = 3;

        for(int i = 1; i <= heroisASeremCriados; i++){
            Heroi heroi = newHeroInstance(i);

            Heroi created = heroiFacade.criaHeroi(heroi);
            log.info("Heroi {} salvo: {}", i, created.toJson(new GsonBuilder()));

            heroi = null;
            created = null;
        }

        List<Heroi> herois = heroisDoUniversoService.buscaHeroisDeUmUniverso(Universo.builder()
                                                    .id(ID_EY_COMICS)
                                                    .build());

        log.info("... Herois Do Universo EY_COMICS ... {}", herois);

        log.info("... Assertions Buscando Herois do Universo ...");
        Assertions.assertTrue(herois.size() == heroisASeremCriados);
    }
}
