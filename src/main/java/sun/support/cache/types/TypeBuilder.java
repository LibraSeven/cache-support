package sun.support.cache.types;

import java.lang.reflect.ParameterizedType;

/**
 * Created by 264929 on 2016/4/18.
 */
public class TypeBuilder {
    public static ParameterizedType listType(Class generic) {
        return new ListParameterizedType(generic);
    }

    public static ParameterizedType mapType(Class keyType, Class valueType) {
        return new MapParameterizedType(keyType, valueType);
    }

    public static ParameterizedType objectType(Class clazz, Class genericType) {
        return new ObjectParameterizedType<>(clazz, genericType);
    }

    public static ParameterizedType setType(Class generic) {
        return new SetParameterizedType(generic);
    }


}
