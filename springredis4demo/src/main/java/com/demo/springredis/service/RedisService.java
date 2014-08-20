package com.demo.springredis.service;


import net.sf.json.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by louis on 2014/8/14.
 */
public interface RedisService {
    public RedisTemplate<String, String> getRedisTemplate();
    public void set(String key, String value);

    public void set(String key, JSONObject value);

    public String get(String key);

    public JSONObject getJSON(String key);

    /**
     *
     * @param key
     * @param value
     * @return the length of the list after the push operations.
     */
    public long lpush(String key,String value);

    public String rpop(String key);

    public long lpush(String key,JSONObject value);

    public JSONObject rpopJSON(String key);



}
