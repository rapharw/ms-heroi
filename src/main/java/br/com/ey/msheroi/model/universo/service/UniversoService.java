package br.com.ey.msheroi.model.universo.service;

import br.com.ey.msheroi.common.exception.universo.UniversoNaoEncontradoException;
import br.com.ey.msheroi.repository.UniversoRepository;
import br.com.ey.msheroi.common.vo.Universo;
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
public class UniversoService {

    @Autowired
    private UniversoRepository universoRepository;


    @Caching(evict = {
                    @CacheEvict(cacheNames = {CACHE_UNIVERSOS}, allEntries = true),
                    @CacheEvict(cacheNames = {CACHE_HEROIS}, allEntries = true)
            }
    )
    @Transactional
    public Universo saveOrUpdate(Universo universo){
        return universoRepository.saveAndFlush(universo);
    }

    @Cacheable(cacheNames = CACHE_UNIVERSOS, key = CACHE_ID)
    @Transactional(readOnly = true)
    public Universo findById(Integer id) {
        Optional<Universo> byId = universoRepository.findById(id);
        return byId.orElseThrow(UniversoNaoEncontradoException::new);
    }

    @Cacheable(cacheNames = CACHE_UNIVERSOS, key = CACHE_ROOT_METHOD_NAME)
    @Transactional(readOnly = true)
    public List<Universo> findAll(){
        return universoRepository.findAll();
    }
}
