package sun.support.cache;

import java.nio.charset.Charset;

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
