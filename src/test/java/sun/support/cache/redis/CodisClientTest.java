package sun.support.cache.redis;

import io.codis.jodis.JedisResourcePool;
import io.codis.jodis.RoundRobinJedisPool;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Created by 264929 on 2016/5/9.
 */
public class CodisClientTest {
    @Test
    public void testCodis() throws Exception{
        JedisResourcePool jedisPool = RoundRobinJedisPool.create()
                .curatorClient("sunyameng:2181", 30000).zkProxyDir("/zk/codis/db_test/proxy").build();
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.set("foo", "bar");
            String value = jedis.get("foo");
            System.out.println(value);
        }
    }
}
