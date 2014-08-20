package com.demo.springredis.service;

/**
 * Created by louis on 2014/8/14.
 */
public interface RedisPublisher {
    public void publish(String topic,String message);
}
