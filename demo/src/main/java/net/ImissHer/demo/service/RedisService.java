package net.ImissHer.demo.service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.*;
import tools.jackson.databind.ObjectMapper;

import java.util.concurrent.TimeUnit;


@Slf4j
@Service
public class RedisService {


        @Autowired
        private RedisTemplate redisTemplate;

    public <T> T get(String key, Class<T> entityClass) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Object o = redisTemplate.opsForValue().get(key);


            return mapper.readValue(o.toString(), entityClass);
        } catch (Exception e) {
            log.error("Exception ", e);
            return null;

        }
    }

    public void set (String key, Object o , long ttl) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonvalue = mapper.writeValueAsString(o) ;
             redisTemplate.opsForValue().set(key , jsonvalue , ttl , TimeUnit.SECONDS);

        } catch (Exception e) {
            log.error("Exception ", e);
        }
    }
}
