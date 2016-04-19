package sun.support.cache.redis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import sun.support.cache.StringCache;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by 264929 on 2016/4/18.
 */
public class RedisStringCache extends StringCache<Object> {

    private RedisTemplate<String, Object> redisTemplate;

    private Gson gson;

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        GsonBuilder gsonBuilder = new GsonBuilder()
                .serializeNulls()
                .setDateFormat("yyyy-MM-ss HH:mm:ss");
        gson = gsonBuilder.create();
    }

    @Override
    public Gson getGson() {
        return this.gson;
    }

    @Override
    public void set(final String key, final Object value, final Class... genericType) {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(toByteArray(key), serialize(value, genericType));
                return null;
            }
        });
    }

    @Override
    public void setEx(final String key, final Object value, final long expire, final Class... genericType) {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.setEx(toByteArray(key), expire, serialize(value, genericType));
                return null;
            }
        });
    }

    @Override
    public void mSet(final Map<String, Object> values, final Class... genericType) {
        redisTemplate.executePipelined(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                for (Map.Entry<String, Object> entry : values.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    connection.set(toByteArray(key), serialize(value, genericType));
                }
                return null;
            }
        });
    }

    @Override
    public <T> T get(final String key, final Class<T> clazz, final Class... genericType) {
        return redisTemplate.execute(new RedisCallback<T>() {
            @Override
            public T doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] v = connection.get(toByteArray(key));
                if (v == null || v.length == 0)
                    return null;
                return deSerialize(toStr(v), clazz, genericType);
            }
        });
    }

    @Override
    public void del(Set<String> keys) {
        redisTemplate.delete(keys);
    }

    @Override
    public void del(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    @Override
    public boolean exists(final String key) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.exists(toByteArray(key));
            }
        });
    }

    @Override
    public void multiDel(String pattern) {
        redisTemplate.delete(keys(pattern));
    }

    @Override
    public long size(final String key) {
        return redisTemplate.execute(new RedisCallback<Integer>() {
            @Override
            public Integer doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.get(toByteArray(key)).length;
            }
        });
    }


    public <T> T rightPop(final String key, final Class<T> clazz, final Class... genericType) {
        return redisTemplate.execute(new RedisCallback<T>() {
            @Override
            public T doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] v = connection.rPop(toByteArray(key));
                if (v == null || v.length == 0)
                    return null;
                return deSerialize(toStr(v), clazz, genericType);
            }
        });
    }

    public <T> void leftPush(final String key, final T value, final Class... genericType) {
        redisTemplate.execute(new RedisCallback<T>() {
            @Override
            public T doInRedis(RedisConnection connection) throws DataAccessException {
                connection.lPush(toByteArray(key), serialize(value, genericType));
                return null;
            }
        });
    }

    public Properties info() {
        return redisTemplate.execute(new RedisCallback<Properties>() {
            @Override
            public Properties doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.info();
            }
        });
    }

    public void flushDB() {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return null;
            }
        });
    }

    public void flushAll() {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushAll();
                return null;
            }
        });
    }


}
