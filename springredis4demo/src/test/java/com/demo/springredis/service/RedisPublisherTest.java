package com.demo.springredis.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class RedisPublisherTest {
    @Autowired
    private RedisPublisher redisPublisher;
    @Test
    public void testPublish() throws Exception {
        redisPublisher.publish("mytopic","hello");
    }
}