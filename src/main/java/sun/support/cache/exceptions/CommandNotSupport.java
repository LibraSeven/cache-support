package sun.support.cache.exceptions;

/**
 * Created by yamorn on 2016/4/18.
 */
public class CommandNotSupport extends RuntimeException {
    public CommandNotSupport(String cmd) {
        super(cmd + " not support");
    }
}
