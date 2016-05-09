package sun.support.cache;

import sun.support.cache.exceptions.LostGenericTypeException;
import sun.support.cache.exceptions.SerializeNotSupport;

import java.nio.charset.Charset;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by 264929 on 2016/4/18.
 */
public abstract class StringCache<V> implements ICache<String, V> {

    protected Charset charset = Charset.forName("UTF-8");

    @Override
    public void destroy() throws Exception {
        //
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //
    }
}
