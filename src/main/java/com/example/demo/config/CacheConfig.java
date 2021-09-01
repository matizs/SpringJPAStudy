package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(CacheConfig.class);
    private final CacheManager cacheManager;

    public CacheConfig(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public void run(String... args) {
        log.info("=========================================================");
        log.info("Using cache manager: " + this.cacheManager.getClass().getName());
        log.info("=========================================================");
    }
}