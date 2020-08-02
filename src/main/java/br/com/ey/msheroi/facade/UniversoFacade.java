package br.com.ey.msheroi.facade;

import br.com.ey.msheroi.exception.universo.ErroAoAlterarUmUniversoException;
import br.com.ey.msheroi.exception.universo.ErroAoBuscarUniversosException;
import br.com.ey.msheroi.exception.universo.UniversoNaoEncontradoException;
import br.com.ey.msheroi.service.UniversoService;
import br.com.ey.msheroi.vo.Universo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UniversoFacade {

    @Autowired
    private UniversoService universoService;

    public Universo buscaUniversoPorId(Integer id) {
        log.info("... Buscando Universo pelo ID {} ...", id);
        try{
            return universoService.findById(id);
        }
        catch (Exception e){
            log.error("buscaUniversoPorId: {}", id, e);
            throw new UniversoNaoEncontradoException();
        }
    }

    public Universo alteraUniverso(Integer id, Universo universo){
        log.info("... Alterando o Universo {} ...", universo);
        try {
            Universo universoEncontrado = buscaUniversoPorId(id);

            universoEncontrado.setDescricao(universo.getDescricao());

            return universoService.saveOrUpdate(universoEncontrado);
        }
        catch (Exception e){
            log.error("alteraUniverso: {} | {}", id, universo, e);
            throw new ErroAoAlterarUmUniversoException();
        }
    }

    public List<Universo> buscaUniversos(){
        log.info("... Buscando todos os Universos ...");
        try{
            return universoService.findAll();
        }
        catch (Exception e){
            log.error("buscaUniversos", e);
            throw new ErroAoBuscarUniversosException();
        }
    }
}
