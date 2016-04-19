package sun.support.cache.utils;


import sun.support.cache.annotations.Cacheable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by yamorn on 2016/3/11.
 * <p>
 * cache key Utility
 */
public final class CacheKeyBuilder {

    private static final String NAMESPACE_SPLIT = "_";
    private static final String KEY_SPLIT = ":";

    /**
     * build full cache key
     *
     * @param clazz        class
     * @param methodName   method name with cacheable annotation
     * @param entity       entity
     * @param paramterType parameter type
     * @return string
     */
    public static String buildCanonicalName(Class<?> clazz, String methodName, Object entity, Class<?>... paramterType) {
        Method method = null;
        try {
            method = clazz.getMethod(methodName, paramterType);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
        Cacheable cacheable = method.getAnnotation(Cacheable.class);
        if (cacheable == null) {
            throw new RuntimeException("No Cacheable");
        }
        String result = null;

        StringBuilder sb = new StringBuilder();
        sb.append(cacheable.namespace()).append(NAMESPACE_SPLIT);
        String[] fieldKeys = cacheable.fieldsKey();
        try {
            for (String fieldName : fieldKeys) {
                fieldName = fieldName.substring(1);
                Field field = entity.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                Object value = field.get(entity);
                sb.append(value).append(KEY_SPLIT);
            }
            result = sb.toString();
            if (result.endsWith(KEY_SPLIT)) {
                result = result.substring(0, result.length() - 1);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
