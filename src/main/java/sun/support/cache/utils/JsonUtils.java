package sun.support.cache.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import org.apache.commons.lang3.StringUtils;
import sun.support.cache.exceptions.LostGenericTypeException;
import sun.support.cache.exceptions.SerializeNotSupport;
import sun.support.cache.types.TypeBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Created by yamorn on 2015/11/11.
 * <p>
 * A simple Json Serializer.
 */
public class JsonUtils {

    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private static Gson gson = null;

    static {
        gson = new GsonBuilder()
                .serializeNulls()
                .setDateFormat(DATE_PATTERN)
                .create();
    }

    /**
     * Try to serialize value to json object.
     */
    public static String toJson(Object value, Class... genericType) {
        if (value == null) {
            return null;
        }
        if (value instanceof String) {
            return value.toString();
        }
        return serialize(value, genericType);
    }

    /**
     * Convert json string to object
     */
    public static <T> T toObject(String json, Class<T> clazz, Class... genericType) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        return deSerialize(json, clazz, genericType);
    }


    /**
     * Valid json string
     */
    public static boolean isGoodJson(String json) {
        if (StringUtils.isEmpty(json)) {
            return false;
        }
        try {
            new JsonParser().parse(json);
        } catch (JsonSyntaxException e) {
            return false;
        }
        return true;
    }

    private static <T> T deSerialize(String value, Class<T> clazz, Class... genericType) {
        T result = null;
        if (Collection.class.isAssignableFrom(clazz)) {
            if (genericType.length == 0)
                throw new LostGenericTypeException();

            if (List.class.isAssignableFrom(clazz)) {
                result = gson.fromJson(value, TypeBuilder.listType(genericType[0]));
            } else if (Set.class.isAssignableFrom(clazz)) {
                result = gson.fromJson(value, TypeBuilder.setType(genericType[0]));
            } else {
                throw new SerializeNotSupport(value.getClass());
            }
        } else if (Map.class.isAssignableFrom(clazz)) {
            if (genericType.length < 2)
                throw new LostGenericTypeException();
            result = gson.fromJson(value, TypeBuilder.mapType(genericType[0], genericType[1]));
        } else {
            if (genericType.length > 0)
                result = gson.fromJson(value, TypeBuilder.objectType(clazz, genericType[0]));
            else
                result = gson.fromJson(value, clazz);
        }
        return result;
    }

    private static String serialize(Object value, Class... genericType) {
        String result = null;
        if (value instanceof Collection<?>) {
            if (genericType.length == 0)
                throw new LostGenericTypeException();
            if (value instanceof List<?>) {
                result = gson.toJson(value, TypeBuilder.listType(genericType[0]));
            } else if (value instanceof Set<?>) {
                result = gson.toJson(value, TypeBuilder.setType(genericType[0]));
            } else {
                throw new SerializeNotSupport(value.getClass());
            }
        } else if (value instanceof Map<?, ?>) {
            if (genericType.length < 2)
                throw new LostGenericTypeException();
            result = gson.toJson(value, TypeBuilder.mapType(genericType[0], genericType[1]));
        } else {
            result = gson.toJson(value);
        }
        return result;
    }


}
