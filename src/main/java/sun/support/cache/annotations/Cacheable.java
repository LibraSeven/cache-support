package sun.support.cache.annotations;

import java.lang.annotation.*;

/**
 * Created by yamorn on 2015/11/10.
 * <p/>
 * This annotation used on methods which would be intercepted by spring AOP.
 * Then build keys and storage in a cache which is usually a redis.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cacheable {

    /*
        A Logical partition namespace of cache.
     */
    String namespace() default "";

    /*
        A set of fields used to build the cache key.
     */
    String[] fieldsKey();

    /*
        expire time. Units are seconds.
     */
    int expire() default -1;

    Class<?>[] genericType() default {};

}
