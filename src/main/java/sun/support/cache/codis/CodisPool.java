package sun.support.cache.codis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisException;
import redis.clients.util.Pool;

/**
 * Created by 264929 on 2016/5/9.
 */
public class CodisPool extends Pool<Jedis> {

    public CodisPool(final JedisPoolConfig poolConfig, final String uris, final int timeout, final int database,
                     final String zkProxyDir) {
        super(poolConfig, new CodisFactory(uris, timeout, zkProxyDir, database, poolConfig));
    }


    @Override
    public Jedis getResource() {
        Jedis jedis = super.getResource();
        jedis.setDataSource(this);
        return jedis;
    }

}
