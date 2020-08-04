package br.com.ey.msheroi.model.heroi.facade;

import br.com.ey.msheroi.common.enums.Situacao;
import br.com.ey.msheroi.common.exception.heroi.*;
import br.com.ey.msheroi.model.heroi.service.HeroiService;
import br.com.ey.msheroi.common.vo.Heroi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class HeroiFacade {

    @Autowired
    private HeroiService heroiService;

    public Heroi buscaHeroiPorId(Long id) {
        log.info("... Buscando Heroi pelo ID {} ...", id);
        try{
            return heroiService.findById(id);
        }
        catch (Exception e){
            log.error("buscaHeroiPorId: {}", id, e);
            throw new HeroiNaoEncontradoException();
        }
    }

    public Heroi criaHeroi(Heroi heroi){
        log.info("... Criando um Heroi ...");
        try {
            heroi.setSituacao(Situacao.ATIVO);
            return heroiService.saveOrUpdate(heroi);
        }
        catch (Exception e){
            log.error("criaHeroi: {}", heroi, e);
            throw new ErroAoCadastrarUmHeroiException();
        }
    }

    public Heroi alteraHeroi(Long id, Heroi heroi){
        log.info("... Alterando o Heroi {} ...", heroi);
        try {
            Heroi heroiEncontrado = buscaHeroiPorId(id);

            heroiEncontrado.setSituacao(heroi.getSituacao());
            heroiEncontrado.setUniverso(heroi.getUniverso());
            heroiEncontrado.setNome(heroi.getNome());
            heroiEncontrado.setPoderes(heroi.getPoderes());

            return heroiService.saveOrUpdate(heroiEncontrado);
        }
        catch (Exception e){
            log.error("alteraHeroi: {} | {}", id, heroi, e);
            throw new ErroAoAlterarUmHeroiException();
        }
    }

    public Heroi atualizaSituacaoHeroi(Long id, Situacao situacao){
        log.info("... Atualizando Situacao Heroi p/ {} ...", situacao.name());

        if(Situacao.ATIVO != situacao && Situacao.INATIVO != situacao)
            throw new SituacaoInexistenteAoAtualizarHeroiException();

        Heroi heroi = null;
        try{
            heroi = this.buscaHeroiPorId(id);
            heroi.setSituacao(situacao);
            return heroiService.saveOrUpdate(heroi);
        }
        catch (Exception e){
            log.error("atualizaSituacaoHeroi: {}", heroi);
            throw new ErroAoAtualizarSituacaoHeroiException(situacao);
        }
    }

    public List<Heroi> buscaHerois(Situacao situacao){
        if(Situacao.isSituacaoValida(situacao))
            return buscaHeroisPorSituacao(situacao);
        else
            return buscaTodosOsHerois();
    }

    private List<Heroi> buscaHeroisPorSituacao(Situacao situacao){
        log.info("... Buscando os Herois pela situacao {} ...", situacao.name());
        try{
            return heroiService.findAllBySituacao(situacao);
        }
        catch (Exception e){
            log.error("buscaHeroisPorSituacao {}", situacao, e);
            throw new ErroAoBuscarHeroisException();
        }
    }

    private List<Heroi> buscaTodosOsHerois(){
        log.info("... Buscando todos os Herois ...");
        try{
            return heroiService.findAll();
        }
        catch (Exception e){
            log.error("buscaTodosOsHerois", e);
            throw new ErroAoBuscarHeroisException();
        }
    }
}
