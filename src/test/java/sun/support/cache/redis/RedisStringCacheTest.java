package sun.support.cache.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.support.cache.Foo;
import sun.support.cache.Person;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 264929 on 2016/5/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class RedisStringCacheTest {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testSet(){
        Foo foo1 = new Foo("haha", 34.55);
        Foo foo2 = new Foo("zzzz", 34.55);
        List<Foo> list = new LinkedList<>();
        list.add(foo1);
        list.add(foo2);
        Person person = new Person("tom", 23,list);
        redisTemplate.opsForValue().set("foo", person);
        person = (Person) redisTemplate.opsForValue().get("foo");
        System.out.println(person.getList().size());
    }
}