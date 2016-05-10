package sun.support.cache.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.support.cache.testbean.Foo;
import sun.support.cache.testbean.Person;
import sun.support.cache.StringCache;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by 264929 on 2016/5/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class RedisStringCacheTest {
    @Autowired
    private StringCache<Object> stringCache;

    @Test
    public void testSet(){
        Foo foo1 = new Foo("haha", 34.55);
        Foo foo2 = new Foo("zzzz", 34.55);
        List<Foo> list = new LinkedList<>();
        list.add(foo1);
        list.add(foo2);
        Person person = new Person("tom", 23,list);
        stringCache.set("foo", person);
        person = stringCache.get("foo");
        System.out.println(person.getList().size());
    }

    @Test
    public void testGet(){
        Person p = stringCache.get("zz");
        assertNull(p);
    }


    @Test
    public void testKeys(){
        stringCache.set("foo_1", "hello");
        stringCache.set("foo_2", "world");
        stringCache.set("foo_3", "welcome");

        Set<String> keys = stringCache.keys("foo_*");
        assertTrue(keys.size() > 0);
    }

    @Test
    public void testMulDel(){
        stringCache.multiDel("foo_*");
        assertTrue(stringCache.keys("foo_*").size() == 0);
    }

    @Test
    public void testExist(){
        stringCache.set("foo", "world");
        assertTrue(stringCache.exists("foo"));
    }

    @Test
    public void testAOP(){
        Person person = new Person("zz", 3);
        Foo foo = person.getFoo("foo_hello", 34);
        System.out.println(foo.getAddress());
    }








}