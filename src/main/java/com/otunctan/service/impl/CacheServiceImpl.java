package com.otunctan.service.impl;

import com.otunctan.constants.CacheNameConstant;
import com.otunctan.service.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class CacheServiceImpl implements CacheService {


    private final RedisTemplate<String, Object> redisTemplate;
    private final CacheManager cacheManager;


    private final Logger logger = LoggerFactory.getLogger(CacheServiceImpl.class);

    public CacheServiceImpl(RedisTemplate<String, Object> redisTemplate, CacheManager cacheManager) {
        this.redisTemplate = redisTemplate;
        this.cacheManager = cacheManager;
    }


    @Override
    public List<Cache> getAllCaches() {
        return this.cacheManager.getCacheNames()
                .stream()
                .map(cacheManager::getCache)
                .collect(Collectors.toList());
    }

    @Override
    public Object get(String cacheName, String key) {
        Cache cache = cacheManager.getCache(cacheName);
        if (Objects.nonNull(cache)) {
            return cache.get(key);
        }
        return null;
    }

    @Override
    public boolean removeCache(String cacheName) {
        Cache cache = this.cacheManager.getCache(cacheName);
        if (Objects.nonNull(cache)) {
            cache.clear();
            return true;
        }
        return false;
    }

    @Override
    public boolean removeCache(String cacheName, String key) {

//        redisTemplate.delete(key);
        Cache cache = this.cacheManager.getCache(cacheName);
        if (Objects.nonNull(cache)) {
            cache.evict(key);
            return true;
        }
        return false;
    }

    @Scheduled(fixedRate = 10,timeUnit = TimeUnit.MINUTES)
    @Caching(evict = {
            @CacheEvict(cacheNames = CacheNameConstant.EMPLOYEES_CACHE, allEntries = true)
    })
    @Override
    public void clearCache() {
        logger.info("clear all cache...");
    }
}
