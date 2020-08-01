package br.com.ey.msheroi.service;

import br.com.ey.msheroi.enums.Situacao;
import br.com.ey.msheroi.exception.heroi.*;
import br.com.ey.msheroi.repository.HeroiRepository;
import br.com.ey.msheroi.vo.Heroi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static br.com.ey.msheroi.config.cache.CacheUtils.*;

@Service
@Slf4j
public class HeroiService {

    @Autowired
    private HeroiRepository heroiRepository;

    @CacheEvict(cacheNames = {CACHE_HEROI, CACHE_COLLECTION_HEROIS}, allEntries = true)
    @Transactional
    public Heroi saveOrUpdate(Heroi heroi){
        return heroiRepository.saveAndFlush(heroi);
    }

    @Cacheable(cacheNames = CACHE_HEROI, key = CACHE_ID, unless = CACHE_ONLY_IF_RESULTS)
    @Transactional(readOnly = true)
    public Heroi findById(Long id) {
        Optional<Heroi> byId = heroiRepository.findById(id);
        return byId.orElseThrow(HeroiNaoEncontradoException::new);
    }

    @Cacheable(cacheNames = CACHE_COLLECTION_HEROIS, key = CACHE_SITUACAO, unless = CACHE_ONLY_IF_RESULTS)
    @Transactional(readOnly = true)
    public List<Heroi> findAllBySituacao(Situacao situacao){
        return heroiRepository.findAllBySituacao(situacao);
    }

    @Cacheable(cacheNames = CACHE_COLLECTION_HEROIS, key = CACHE_ROOT_METHOD_NAME, unless = CACHE_ONLY_IF_RESULTS)
    @Transactional(readOnly = true)
    public List<Heroi> findAll(){
        return heroiRepository.findAll();
    }
}