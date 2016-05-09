package sun.support.cache.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import sun.support.cache.StringCache;
import sun.support.cache.annotations.CacheUpdate;
import sun.support.cache.annotations.Cacheable;
import sun.support.cache.utils.AopUtils;

import java.lang.reflect.Method;

/**
 * Created by yamorn on 2015/11/10.
 * <p>
 * Cache aspect used to intercept method which has @Cacheable annotation on it.
 * Then do the cache job.
 * Note: methods which are intercepted must not have primitive type arguments.
 */

@Aspect
@Component
public class CacheAspect {
    private static final String NAMESPACE_SPLIT = "_";
    private static final String KEY_SPLIT = ":";

    private static final Logger logger = LoggerFactory.getLogger(CacheAspect.class);

    @Autowired
    private StringCache<Object> cacheStorage;

    //========================================================================
    //                          Cache storage AOP
    //========================================================================

    @Pointcut("@annotation(sun.support.cache.annotations.Cacheable)")
    public void cacheAdvice() {
    }

    @Around("cacheAdvice()")
    public Object cache(ProceedingJoinPoint pjp) throws Throwable {
        Object retObj = null;

        Method method = AopUtils.getMethod(pjp);
        assert method != null;
        Cacheable cacheable = method.getAnnotation(Cacheable.class);

        /**
         * The cacheKey is the full name of redis cache key
         */
        String namespace = cacheable.namespace();
        String[] fieldsKey = cacheable.fieldsKey();
        String cacheKey = parseKey(namespace, fieldsKey, method, pjp.getArgs());
        Class<?> returnType = ((MethodSignature) pjp.getSignature()).getReturnType();

        Class<?>[] genericType = cacheable.genericType();

        try {
//            retObj = cacheStorage.get(cacheKey, returnType, genericType);

            if (retObj == null) {

                try {
                    retObj = pjp.proceed();
                    // Not cache Null object
                    if (retObj != null) {
                        int expire = cacheable.expire();
                        if (expire > 0) {
//                            cacheStorage.setEx(cacheKey, retObj, expire, genericType);
                        } else {
//                            cacheStorage.set(cacheKey, retObj, genericType);
                        }
                    }
                } catch (Throwable e) {
                    logger.error(e.getMessage());
                }
            } else {
                logger.info("Get " + cacheKey + " from cache.");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return retObj;
    }


    /**
     * Parse key and build a redis key with namespace.
     * The key's definition is support the SpEL Expression
     */
    private String parseKey(String namespace, String[] fieldsKey, Method method, Object[] args) {
        StringBuilder sb = new StringBuilder();
        /**
         * Get method parameters using the spring support library.
         */
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNameArray = u.getParameterNames(method);
        /**
         * Put all the parameters into SpEL context and analysis key using SpEL
         */
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < paramNameArray.length; i++) {
            context.setVariable(paramNameArray[i], args[i]);
        }

        sb.append(namespace).append(NAMESPACE_SPLIT);
        for (String key : fieldsKey) {
            String value = parser.parseExpression(key).getValue(context, String.class);
            sb.append(value).append(KEY_SPLIT);
        }
        String fullKey = sb.toString();
        int index;
        if (fullKey.length() > 0 && (index = fullKey.lastIndexOf(":")) > 0) {
            fullKey = fullKey.substring(0, index);
        }
        return fullKey;
    }

    @AfterThrowing(pointcut = "@annotation(sun.support.cache.annotations.Cacheable)",
            throwing = "ex")
    public void doException(Exception ex) {
        logger.error(ex.getLocalizedMessage());
    }


    //========================================================================
    //                          Cache Update AOP
    //========================================================================

    @Pointcut("@annotation(sun.support.cache.annotations.CacheUpdate)")
    public void cacheUpdateAdvice() {
    }

    @AfterReturning(pointcut = "cacheUpdateAdvice()", returning = "rtv")
    public void cacheUpdate(JoinPoint jp, Object rtv) {
        Method method = AopUtils.getMethod(jp);
        assert method != null;

        CacheUpdate cacheUpdate = method.getAnnotation(CacheUpdate.class);
        /**
         * The cacheKey is the full name of redis cache key
         */
        String namespace = cacheUpdate.namespace();
        String[] fieldsKey = cacheUpdate.fieldsKey();
        String cacheKey = parseKey(namespace, fieldsKey, method, jp.getArgs());

        Class type = cacheUpdate.valueType();
        int expire = cacheUpdate.expire();
        boolean updateRetVal = cacheUpdate.updateRetVal();

        Object value;
        if (updateRetVal) {
            value = rtv;
        } else {
            String valueField = cacheUpdate.valueField();
            value = getUpdateFieldValue(valueField, method, jp, type);
        }

        if (expire > 0) {
            cacheStorage.setEx(cacheKey, value, expire);
        } else {
            cacheStorage.set(cacheKey, value);
        }
    }

    private Object getUpdateFieldValue(String valueField, Method method, JoinPoint jp, Class valueType) {
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNameArray = u.getParameterNames(method);
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < paramNameArray.length; i++) {
            context.setVariable(paramNameArray[i], jp.getArgs()[i]);
        }
        return parser.parseExpression(valueField).getValue(context, valueType);
    }

}
