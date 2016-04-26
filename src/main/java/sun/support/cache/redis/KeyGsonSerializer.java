package sun.support.cache.redis;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

/**
 * Created by yamorn on 2016/4/26.
 */
public class KeyGsonSerializer implements RedisSerializer<String> {
    private Charset charset = Charset.forName("UTF-8");

    @Override
    public byte[] serialize(String s) throws SerializationException {
        if(StringUtils.isEmpty(s))
            return new byte[0];
        return s.getBytes(charset);
    }

    @Override
    public String deserialize(byte[] bytes) throws SerializationException {
        return new String(bytes, charset);
    }
}
