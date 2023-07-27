package com.otunctan.controller;


import com.otunctan.service.CacheService;
import org.springframework.cache.Cache;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/rest/api/caches")
public class CacheController {


    private final CacheService cacheService;

    public CacheController(CacheService cacheService) {
        this.cacheService = cacheService;
    }


    @GetMapping(path = "")
    public List<Cache> getAllEmployees() {
        return this.cacheService.getAllCaches();
    }

    @GetMapping(path = "/get/{cacheName}/{key}")
    public Object getCacheByKey(@PathVariable("cacheName") String cacheName, @PathVariable("key") String key) {
        return this.cacheService.get(cacheName, key);
    }


    @GetMapping(path = "/delete/{cacheName}")
    public boolean deleteCache(@PathVariable("cacheName") String cacheName) {
        return this.cacheService.removeCache(cacheName);
    }

    @GetMapping(path = "/delete/{cacheName}/{key}")
    public boolean deleteCache(@PathVariable("cacheName") String cacheName, @PathVariable("key") String key) {
        return this.cacheService.removeCache(cacheName, key);
    }
}
