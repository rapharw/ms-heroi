package br.com.ey.msheroi.service;

import br.com.ey.msheroi.enums.TipoSituacaoEnum;
import br.com.ey.msheroi.exception.*;
import br.com.ey.msheroi.repository.HeroiRepository;
import br.com.ey.msheroi.vo.Heroi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class HeroiService {

    @Autowired
    private HeroiRepository heroiRepository;

    public Heroi buscaHeroiPorId(Long id) {
        log.info("... Buscando Heroi pelo ID {} ...", id);

        try{
            Optional<Heroi> byId = heroiRepository.findById(id);
            return byId.orElseThrow(HeroiNaoEncontradoException::new);
        }
        catch (Exception e){
            log.error("buscaHeroiPorId: {}", id, e);
            throw new HeroiNaoEncontradoException();
        }
    }

    public Heroi criaHeroi(Heroi heroi){
        log.info("... Criando um Heroi ...");

        try {
            heroi.setSituacao(TipoSituacaoEnum.ATIVO);
            return heroiRepository.save(heroi);
        }
        catch (Exception e){
            log.error("criaHeroi: {}", heroi, e);
            throw new ErroAoCadastrarUmHeroiException();
        }
    }


    public Heroi alteraHeroi(Long id, Heroi heroiAlteracao){
        log.info("... Alterando o Heroi {} ...", heroiAlteracao);

        try {
            Heroi heroiEncontrado = buscaHeroiPorId(id);

            heroiEncontrado.setSituacao(heroiAlteracao.getSituacao());
            heroiEncontrado.setUniverso(heroiAlteracao.getUniverso());
            heroiEncontrado.setNome(heroiAlteracao.getNome());

            heroiEncontrado.setPoderes(heroiAlteracao.getPoderes());

            return heroiRepository.save(heroiEncontrado);
        }
        catch (Exception e){
            log.error("alteraHeroi: {} | {}", id, heroiAlteracao, e);
            throw new ErroAoAlterarUmHeroiException();
        }
    }


    public Heroi ativaHeroi(Long id){
        log.info("... Ativando o Heroi de ID {} ...", id);
        try {
            return atualizaSituacaoHeroi(id, TipoSituacaoEnum.ATIVO);
        }
        catch (Exception e){
            log.error("ativaHeroi: {}", id);
            throw new ErroAoAtualizarSituacaoHeroiException(TipoSituacaoEnum.ATIVO);
        }
    }

    public Heroi inativaHeroi(Long id){
        log.info("... Inativando o Heroi de ID {} ...", id);
        try {
            return atualizaSituacaoHeroi(id, TipoSituacaoEnum.INATIVO);
        }
        catch (Exception e){
            log.error("ativaHeroi: {}", id);
            throw new ErroAoAtualizarSituacaoHeroiException(TipoSituacaoEnum.INATIVO);
        }
    }

    private Heroi atualizaSituacaoHeroi(Long id, TipoSituacaoEnum tipoSituacaoEnum){
        Heroi heroi = this.buscaHeroiPorId(id);
        heroi.setSituacao(tipoSituacaoEnum);
        return heroiRepository.save(heroi);
    }


    public List<Heroi> buscaHerois(TipoSituacaoEnum tipoSituacaoEnum){
        switch (tipoSituacaoEnum){
            case ATIVO:
                return buscaHeroisPorSituacao(TipoSituacaoEnum.ATIVO);
            case INATIVO:
                return buscaHeroisPorSituacao(TipoSituacaoEnum.INATIVO);
            case TODOS:
                return buscaTodosOsHerois();
            default:
                return Collections.emptyList();
        }
    }


    private List<Heroi> buscaHeroisPorSituacao(TipoSituacaoEnum tipoSituacaoEnum){
        log.info("... Buscando os Herois pela situacao {} ...", tipoSituacaoEnum.name());
        try{
            return heroiRepository.findAllBySituacao(tipoSituacaoEnum);
        }
        catch (Exception e){
            log.error("buscaHeroisPorSituacao", e);
            throw new ErroAoBuscarHeroisException(tipoSituacaoEnum);
        }
    }
    private List<Heroi> buscaTodosOsHerois(){
        log.info("... Buscando todos os Herois ...");
        try{
            return heroiRepository.findAll();
        }
        catch (Exception e){
            log.error("buscaTodosOsHerois", e);
            throw new ErroAoBuscarHeroisException(TipoSituacaoEnum.TODOS);
        }
    }
}
