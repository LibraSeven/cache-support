package sun.support.cache.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

/**
 * Created by 264929 on 2016/5/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class CodisStringCacheTest {
    @Autowired
    private CodisStringCache cache;


    @Test
    public void testSet() {
        cache.set("hello", "world");
        assertTrue("world".equals(cache.get("hello")));
    }
}