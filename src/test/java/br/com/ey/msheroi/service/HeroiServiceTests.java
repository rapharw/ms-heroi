package br.com.ey.msheroi.service;

import br.com.ey.msheroi.enums.Situacao;
import br.com.ey.msheroi.facade.HeroiFacade;
import br.com.ey.msheroi.vo.Heroi;
import br.com.ey.msheroi.vo.Poder;
import br.com.ey.msheroi.vo.Universo;
import com.google.gson.GsonBuilder;
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
public class HeroiServiceTests {

    @Autowired
    private HeroiFacade heroiFacade;

    @Test
    @Order(1)
    public void criaUmHeroiEProcuraPeloIdAposACriacao(){

        //Cria uma instancia de um heroi
        Heroi heroi = Heroi.builder()
                            .nome(NOME_HEROI)
                            .situacao(Situacao.ATIVO)
                            .universo(Universo.builder().id(ID_EY_COMICS).build())
                            .build();

        //Adiciona um poder p/ o Heroi
        heroi.adicionaUmPoder(Poder.builder()
                                    .id(ID_PODER_PALACIO_DA_MEMORIA)
                                    .build());


        Heroi heroiCriadoComSucesso = heroiFacade.criaHeroi(heroi);

        Heroi heroiCriado = heroiFacade.buscaHeroiPorId(heroiCriadoComSucesso.getId());

        log.info("Heroi criado: {}", heroiCriado.toJson(new GsonBuilder()));

        log.info("... Assertions Heroi Criado ...");
        log.info("... Not Null ...");
        Assertions.assertNotNull(heroiCriado);
        log.info("... Nome Equals ...");
        Assertions.assertEquals(NOME_HEROI, heroiCriado.getNome());
        log.info("... 1 poder apenas ...");
        Assertions.assertEquals(1, heroiCriado.getPoderes().size());
        Optional<Poder> firstPoder = heroiCriado.getPoderes().stream()
                                                            .findFirst();
        Assertions.assertTrue(firstPoder.isPresent());
        Poder poder = firstPoder.get();
        log.info("... Poder do Palacio da Memoria ...");
        Assertions.assertEquals(DESCRICAO_PODER_PALACIO_DA_MEMORIA_EXPECTED, poder.getDescricao());
        log.info("... Universo ...");
        Assertions.assertEquals(DESCRICAO_EY_COMICS_EXPECTED, heroiCriado.getUniverso().getDescricao());
    }

    @Test
    @Order(2)
    public void inativandoUmHeroi(){
        Long idDoHeroiASerInativado = 1L;
        Heroi heroi = heroiFacade.atualizaSituacaoHeroi(idDoHeroiASerInativado, Situacao.INATIVO);
        log.info("... Assertions Inativando o Heroi ...");
        Assertions.assertTrue(!heroi.isAtivo());
    }

    @Test
    @Order(3)
    public void ativandoUmHeroi(){
        Long idDoHeroiASerAtivado = 1L;
        Heroi heroi = heroiFacade.atualizaSituacaoHeroi(idDoHeroiASerAtivado, Situacao.ATIVO);
        log.info("... Assertions Ativando o Heroi ...");
        Assertions.assertTrue(heroi.isAtivo());
    }

    @Test
    @Order(4)
    public void alteraNomeDoHeroi(){
        Long idDoHeroiASerAlteradoONome = 1L;
        Heroi heroi = heroiFacade.buscaHeroiPorId(idDoHeroiASerAlteradoONome);

        String nomeASerAlterado = "Arya Stark";
        heroi.setNome(nomeASerAlterado);

        Heroi heroiComNomeAlterado = heroiFacade.alteraHeroi(idDoHeroiASerAlteradoONome, heroi);
        log.info("... Assertions Alterando o nome do Heroi ...");

        Assertions.assertEquals(nomeASerAlterado, heroiComNomeAlterado.getNome());
    }

    @Test
    @Order(5)
    public void adicionaUmNovoPoderParaOHeroi(){
        Long idDoHeroiASerAdicionadoUmPoder = 1L;

        Heroi heroi = heroiFacade.buscaHeroiPorId(idDoHeroiASerAdicionadoUmPoder);
        log.info("... Heroi antes ... {}", heroi);
        Poder novoPoder = Poder.builder()
                                .id(ID_PODER_SUPER_VELOCIDADE)
                                .build();
        heroi.adicionaUmPoder(novoPoder);

        heroiFacade.alteraHeroi(idDoHeroiASerAdicionadoUmPoder, heroi);
        Heroi heroiAlterado = heroiFacade.buscaHeroiPorId(idDoHeroiASerAdicionadoUmPoder);

        log.info("... Heroi Depois ... {}", heroiAlterado);

        log.info("... Assertions Adicionando um Poder ao Heroi ...");
        Optional<Poder> firstPoder = heroiAlterado.getPoderes().stream()
                                                        .filter(poder -> DESCRICAO_PODER_SUPER_VELOCIDADE_EXPECTED.equals(poder.getDescricao()))
                                                        .findFirst();
        Assertions.assertTrue(firstPoder.isPresent());
        Poder poder = firstPoder.get();
        log.info("... Poder da Super velocidade ...");
        Assertions.assertEquals(DESCRICAO_PODER_SUPER_VELOCIDADE_EXPECTED, poder.getDescricao());
    }

    @Test
    @Order(6)
    public void removeUmPoderDoHeroi(){
        Long idDoHeroiASerRemovidoUmPoder = 1L;

        Heroi heroi = heroiFacade.buscaHeroiPorId(idDoHeroiASerRemovidoUmPoder);
        log.info("... Heroi antes ... {}", heroi);
        Poder poderASerRemovido = Poder.builder()
                                        .id(ID_PODER_PALACIO_DA_MEMORIA)
                                        .build();
        heroi.removeUmPoder(poderASerRemovido);

        heroiFacade.alteraHeroi(idDoHeroiASerRemovidoUmPoder, heroi);
        Heroi heroiAlterado = heroiFacade.buscaHeroiPorId(idDoHeroiASerRemovidoUmPoder);

        log.info("... Heroi Depois ... {}", heroiAlterado);

        log.info("... Assertions Removendo um Poder do Heroi ...");
        Optional<Poder> firstPoder = heroiAlterado.getPoderes().stream()
                                            .filter(poder -> ID_PODER_PALACIO_DA_MEMORIA == poder.getId())
                                            .findFirst();
        log.info("... Poder do Palacio da Memoria Inexistente ...");
        Assertions.assertTrue(!firstPoder.isPresent());
    }
}
