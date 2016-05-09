package sun.support.cache.codis;

import io.codis.jodis.JedisResourcePool;
import io.codis.jodis.RoundRobinJedisPool;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.BinaryJedis;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisException;

/**
 * Created by 264929 on 2016/5/9.
 */
public class CodisFactory implements PooledObjectFactory<Jedis> {

    private final int database;

    private JedisResourcePool jedisResourcePool;

    public CodisFactory(final String uris, final int timeout, final String zkProxyDir, final int database,
                        JedisPoolConfig poolConfig) {

        this.database = database;
        jedisResourcePool = RoundRobinJedisPool.create()
                .curatorClient(uris, timeout)
                .zkProxyDir(zkProxyDir).poolConfig(poolConfig).build();

    }

    @Override
    public void activateObject(PooledObject<Jedis> pooledJedis) throws Exception {
        final BinaryJedis jedis = pooledJedis.getObject();
        if (jedis.getDB() != database) {
            jedis.select(database);
        }
    }

    @Override
    public void destroyObject(PooledObject<Jedis> pooledJedis) throws Exception {
        final BinaryJedis jedis = pooledJedis.getObject();
        if (jedis.isConnected()) {
            try {
                try {
                    jedis.quit();
                } catch (Exception e) {
                    // empty
                }
                jedis.disconnect();
            } catch (Exception e) {
                // empty
            }
        }
    }

    @Override
    public PooledObject<Jedis> makeObject() throws Exception {
        final Jedis jedis = jedisResourcePool.getResource();

        try {
            jedis.connect();
            if (database != 0) {
                jedis.select(database);
            }
        } catch (JedisException je) {
            jedis.close();
            throw je;
        }

        return new DefaultPooledObject<>(jedis);
    }

    @Override
    public void passivateObject(PooledObject<Jedis> pooledJedis) throws Exception {
        // TODO maybe should select db 0? Not sure right now.
    }

    @Override
    public boolean validateObject(PooledObject<Jedis> pooledJedis) {
        final BinaryJedis jedis = pooledJedis.getObject();
        try {
            return jedis.isConnected() && jedis.ping().equals("PONG");
        } catch (final Exception e) {
            return false;
        }
    }
}
