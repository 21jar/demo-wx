package com.demo.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;

/**
 * Created by 胡超云 on 2016/11/17.
 */

@Slf4j
@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {

    @Bean
    public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        //设置key-value超时时间，默认30分钟
        cacheManager.setDefaultExpiration(60 * 30);
        return cacheManager;
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        //如果采用redis默认的序列方式，那么下面的代码不能写，否则如果参数中存在非String类型参数则会报错
        //但是默认key生成策略的一个缺点就是通过redis客户端查看key得到的是乱码

        //采用自定义key生成策略的时候(也就是下面重写了KeyGenerator生成策略)，需要加上下面自定义key实现并注册到redis模板中
        RedisSerializer<String> serializer = new StringRedisSerializer();
        template.setKeySerializer(serializer);
        template.setHashKeySerializer(serializer);

        return template;
    }

    @Override
    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... args) {
                StringBuilder builder = new StringBuilder();
                builder.append(o.getClass().getName())
                        .append(method.getName());
                for (Object arg : args) {
                    if(arg != null)
                       builder.append(arg.toString());
                }
                log.info("keyGenerator: " + builder.toString());
                return builder.toString();
            }
        };
    }
}
