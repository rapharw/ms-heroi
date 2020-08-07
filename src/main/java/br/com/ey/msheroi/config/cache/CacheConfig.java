package br.com.ey.msheroi.config.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@EnableCaching
@Slf4j
public class CacheConfig {

    @PostConstruct
    private void init() {
        log.info("... Enabling Cache ...");
    }
}
