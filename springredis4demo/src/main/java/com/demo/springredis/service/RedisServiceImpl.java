package com.demo.springredis.service;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by louis on 2014/8/14.
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public RedisTemplate<String, String> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void set(String key, JSONObject jsonObject) {
        redisTemplate.opsForValue().set(key,jsonObject.toString());
    }

    @Override
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key,value);
    }

    @Override
    public String get(String key) {
        return  redisTemplate.opsForValue().get(key);
    }

    @Override
    public JSONObject getJSON(String key) {
        String result=redisTemplate.opsForValue().get(key);
        if(result!=null) {
            return JSONObject.fromObject(result);
        }
        return null;
    }

    @Override
    public long lpush(String key, String value) {
        return redisTemplate.opsForList().leftPush(key,value);
    }

    @Override
    public String rpop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    @Override
    public long lpush(String key, JSONObject value) {
        return redisTemplate.opsForList().leftPush(key,value.toString());
    }

    @Override
    public JSONObject rpopJSON(String key) {
        Object obj= redisTemplate.opsForList().rightPop(key);;
        if(obj!=null) {
            return JSONObject.fromObject(obj);
        }
        return null;
    }
}
