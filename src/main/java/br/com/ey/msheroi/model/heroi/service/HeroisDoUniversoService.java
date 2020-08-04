package br.com.ey.msheroi.model.heroi.service;

import br.com.ey.msheroi.repository.HeroiRepository;
import br.com.ey.msheroi.common.vo.Heroi;
import br.com.ey.msheroi.common.vo.Universo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroisDoUniversoService {

    @Autowired
    private HeroiRepository heroiRepository;

    public List<Heroi> buscaHeroisDeUmUniverso(Universo universo){
        return heroiRepository.findAllByUniverso(universo);
    }
}
