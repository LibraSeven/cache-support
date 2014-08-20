package com.demo.springredis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by louis on 2014/8/14.
 */
@Service
public class RedisPublisherImpl implements RedisPublisher {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Override
    public void publish(String topic, String message) {
        redisTemplate.convertAndSend(topic,message);
    }
}
