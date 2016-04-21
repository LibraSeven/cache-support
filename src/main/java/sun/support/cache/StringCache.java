package sun.support.cache;

import com.google.gson.Gson;
import sun.support.cache.exceptions.LostGenericTypeException;
import sun.support.cache.exceptions.SerializeNotSupport;
import sun.support.cache.types.TypeBuilder;

import java.nio.charset.Charset;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by 264929 on 2016/4/18.
 */
public abstract class StringCache<V> implements ICache<String, V> {

    private static Charset charset = Charset.forName("UTF-8");

    protected byte[] toByteArray(String key) {
        return key.getBytes(charset);
    }

    protected String toStr(byte[] bytes) {
        return new String(bytes, charset);
    }

    protected byte[] serialize(Object value, Class... genericType) {
        if(value instanceof String){
            return ((String) value).getBytes(charset);
        }
        String result = null;
        if (value instanceof Collection<?>) {
            if (genericType.length == 0)
                throw new LostGenericTypeException();
            if (value instanceof List<?>) {
                result = getGson().toJson(value, TypeBuilder.listType(genericType[0]));
            } else if (value instanceof Set<?>) {
                result = getGson().toJson(value, TypeBuilder.setType(genericType[0]));
            } else {
                throw new SerializeNotSupport(value.getClass());
            }
        } else if (value instanceof Map<?, ?>) {
            if (genericType.length < 2)
                throw new LostGenericTypeException();
            result = getGson().toJson(value, TypeBuilder.mapType(genericType[0], genericType[1]));
        } else {
            result = getGson().toJson(value);
        }
        return result != null ? toByteArray(result) : new byte[0];
    }

    protected <T> T deSerialize(String value, Class<T> clazz, Class... genericType) {
        T result = null;
        if (Collection.class.isAssignableFrom(clazz)) {
            if (genericType.length == 0)
                throw new LostGenericTypeException();

            if (List.class.isAssignableFrom(clazz)) {
                result = getGson().fromJson(value, TypeBuilder.listType(genericType[0]));
            } else if (Set.class.isAssignableFrom(clazz)) {
                result = getGson().fromJson(value, TypeBuilder.setType(genericType[0]));
            } else {
                throw new SerializeNotSupport(value.getClass());
            }
        } else if (Map.class.isAssignableFrom(clazz)) {
            if (genericType.length < 2)
                throw new LostGenericTypeException();
            result = getGson().fromJson(value, TypeBuilder.mapType(genericType[0], genericType[1]));
        } else {
            if(genericType.length > 0)
                result = getGson().fromJson(value,  TypeBuilder.objectType(clazz, genericType[0]));
            else
                result = getGson().fromJson(value, clazz);
        }
        return result;
    }

    public abstract Gson getGson();


    @Override
    public void destroy() throws Exception {
        //
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //
    }
}
