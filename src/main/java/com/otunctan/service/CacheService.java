package com.otunctan.service;

import com.otunctan.constants.CacheNameConstant;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

public interface CacheService {
    List<Cache> getAllCaches();

    Object get(String cacheName, String key);

    boolean removeCache(String cacheName);

    boolean removeCache(String cacheName, String key);

    /**
     *
     */
    void clearCache();


}
