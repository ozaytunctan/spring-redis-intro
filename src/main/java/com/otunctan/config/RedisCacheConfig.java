package com.otunctan.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;

@Configuration
public class RedisCacheConfig {


    @Bean
    @Primary
    public RedisProperties redisProperties() {
        return new RedisProperties();
    }


    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(60))
                .disableCachingNullValues()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }


//    @Bean
//    public LettucePoolingClientConfiguration poolingClientConfiguration() {
//
//        return LettucePoolingClientConfiguration.builder()
//                .poolConfig(genericObjectPoolConfig)
//                .build();
//    }


    @Bean
    @Primary
    public LettuceConnectionFactory lettuceConnectionFactory(@Qualifier("redisProperties") RedisProperties redisProperties
                                                         ) {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(redisProperties.getHost());
        configuration.setPort(redisProperties.getPort());

        LettuceClientConfiguration lettuceClientConfiguration= LettuceClientConfiguration.builder()

                .build();
        return new LettuceConnectionFactory(configuration,lettuceClientConfiguration);
    }
//
    @Bean
    public RedisTemplate<String, Object> redisTemplate(@Qualifier("lettuceConnectionFactory") RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setValueSerializer(RedisSerializer.json());
//        template.setKeySerializer(RedisSerializer.json());
        // Add some specific configuration here. Key serializers, etc.
        return template;
    }
//
    @Bean
    public CacheManager alternateCacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager("employees","demo");
        cacheManager.setAllowNullValues(false);
        return cacheManager;
    }

}
