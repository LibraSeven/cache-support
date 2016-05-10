package sun.support.cache.redis;

import sun.support.cache.exceptions.CommandNotSupport;

import java.util.Properties;
import java.util.Set;

/**
 * Created by 264929 on 2016/5/10.
 *
 * Commands are disallowed in codis proxy.
 *
 * See https://github.com/CodisLabs/codis/blob/master/doc/unsupported_cmds.md
 */
public class CodisStringCache extends RedisStringCache {
    @Override
    public Set<String> keys(String pattern) {
        throw new CommandNotSupport("KEYS");
    }

    @Override
    public void flushDB() {
        throw new CommandNotSupport("FLUSHDB");
    }

    @Override
    public void flushAll() {
        throw new CommandNotSupport("FLUSHALL");
    }

    @Override
    public Properties info() {
        throw new CommandNotSupport("INFO");
    }
}
