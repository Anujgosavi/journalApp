package net.ImissHer.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate ;
    @Test
    public void getemail(){

        redisTemplate.opsForValue().set("Email" ,"anuj@gmail.com");

        Object sal = redisTemplate.opsForValue().get("salary");

        System.out.print(sal);

    }
}
