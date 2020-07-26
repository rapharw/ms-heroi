package br.com.ey.msheroi.service;

import br.com.ey.msheroi.repository.HeroiRepository;
import br.com.ey.msheroi.vo.Heroi;
import br.com.ey.msheroi.vo.Universo;
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
