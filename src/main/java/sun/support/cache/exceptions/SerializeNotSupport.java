package sun.support.cache.exceptions;

/**
 * Created by yamorn on 2016/4/18.
 */
public class SerializeNotSupport extends RuntimeException {
    public SerializeNotSupport(Class<?> clazz) {
        super("Type " + clazz.getName() + "Serialize not support");
    }
}
