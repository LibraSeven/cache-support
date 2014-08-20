package com.demo.springredis.service;

import net.sf.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class RedisServiceTest {
    @Autowired
    private RedisService redisService;
    @Test
    @Ignore
    public void testSet() throws Exception {
        redisService.set("foo",new JSONObject().accumulate("a","b"));
    }

    @Test
    @Ignore
    public void testGet() throws Exception {
        Object ojb = redisService.get("foo");
        System.out.println(ojb);
    }
    @Test
    public void testLpush() {
        redisService.lpush("split", "{\"jobId\":\"8e0565e8-0e25-43df-b50d-11a4ff6df770\",\"jobParameters\":{\"show\":\"num\"},\"userId\":\"louis\"}");
    }
    @Test
    @Ignore
    public void testRpop() {
//        String result = null;
//        while ((result = redisService.rpop("mylist")) != null) {
//            System.out.println(result);
//        }
        RedisTemplate<String,String> redisTemplate=redisService.getRedisTemplate();
    }
}