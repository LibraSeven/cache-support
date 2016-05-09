package sun.support.cache.codis;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.util.Pool;
import sun.support.cache.codis.CodisPool;

/**
 * Created by 264929 on 2016/5/9.
 */
public class CodisConnectionFactory extends JedisConnectionFactory {

    private String uris;

    private String zkProxyDir;

    private int database;

    private int timeout;


    @Override
    protected Pool<Jedis> createRedisPool() {
        return new CodisPool(getPoolConfig(), this.uris, this.timeout, this.database, this.zkProxyDir);
    }

    public String getUris() {
        return uris;
    }

    public void setUris(String uris) {
        this.uris = uris;
    }

    public String getZkProxyDir() {
        return zkProxyDir;
    }

    public void setZkProxyDir(String zkProxyDir) {
        this.zkProxyDir = zkProxyDir;
    }

    @Override
    public int getDatabase() {
        return database;
    }

    @Override
    public void setDatabase(int database) {
        this.database = database;
    }

    @Override
    public int getTimeout() {
        return timeout;
    }

    @Override
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
