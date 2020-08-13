package br.com.ey.msheroi.model.poder.facade;

import br.com.ey.msheroi.common.exception.poder.*;
import br.com.ey.msheroi.model.poder.service.PoderService;
import br.com.ey.msheroi.common.vo.Poder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PoderFacade {

    @Autowired
    private PoderService poderService;

    public Poder buscaPoderPorId(Integer id) {
        log.info("... Buscando Poder pelo ID {} ...", id);
        try{
            return poderService.findById(id);
        }
        catch (Exception e){
            log.error("buscaPoderPorId: {}", id, e);
            throw new PoderNaoEncontradoException();
        }
    }

    public Poder criaPoder(Poder poder){
        log.info("... Criando um Poder ...");
        try {
            poder.setId(null);
            return poderService.saveOrUpdate(poder);
        }
        catch (Exception e){
            log.error("criaPoder: {}", poder, e);
            throw new ErroAoCadastrarUmPoderException();
        }
    }

    public Poder alteraPoder(Integer id, Poder poder){
        log.info("... Alterando o Poder {} ...", poder);
        try {
            Poder poderEncontrado = buscaPoderPorId(id);

            poderEncontrado.setDescricao(poder.getDescricao());

            return poderService.saveOrUpdate(poderEncontrado);
        }
        catch (Exception e){
            log.error("alteraPoder: {} | {}", id, poder, e);
            throw new ErroAoAlterarUmPoderException();
        }
    }

    public List<Poder> buscaPoderes(){
        log.info("... Buscando todos os Poderes ...");
        try{
            return poderService.findAll();
        }
        catch (Exception e){
            log.error("buscaPoderes", e);
            throw new ErroAoBuscarPoderesException();
        }
    }

    public void removePoder(Integer id){
        log.info("... Removendo o Poder de ID {} ...", id);
        try{
            poderService.remove(id);
        }
        catch (Exception e){
            log.error("removePoder", e);
            throw new ErroAoRemoverPoderException();
        }
    }
}
