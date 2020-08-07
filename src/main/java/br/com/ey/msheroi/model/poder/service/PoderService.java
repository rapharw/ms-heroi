package br.com.ey.msheroi.model.poder.service;

import br.com.ey.msheroi.common.exception.poder.PoderNaoEncontradoException;
import br.com.ey.msheroi.repository.PoderRepository;
import br.com.ey.msheroi.common.vo.Poder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static br.com.ey.msheroi.config.cache.CacheUtils.*;

@Service
@Slf4j
public class PoderService {

    @Autowired
    private PoderRepository poderRepository;


    @Caching(evict = {
                    @CacheEvict(cacheNames = {CACHE_PODERES}, allEntries = true),
                    @CacheEvict(cacheNames = {CACHE_HEROIS}, allEntries = true)
            }
    )
    @Transactional
    public Poder saveOrUpdate(Poder poder){
        return poderRepository.saveAndFlush(poder);
    }

    @Cacheable(cacheNames = CACHE_PODERES, key = CACHE_ID)
    @Transactional(readOnly = true)
    public Poder findById(Integer id) {
        Optional<Poder> byId = poderRepository.findById(id);
        return byId.orElseThrow(PoderNaoEncontradoException::new);
    }

    @Cacheable(cacheNames = CACHE_PODERES, key = CACHE_ROOT_METHOD_NAME)
    @Transactional(readOnly = true)
    public List<Poder> findAll(){
        return poderRepository.findAll();
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = {CACHE_PODERES}, allEntries = true),
            @CacheEvict(cacheNames = {CACHE_HEROIS}, allEntries = true)
    }
    )
    @Transactional
    public void remove(Integer id){
        poderRepository.deleteById(id);
    }
}
