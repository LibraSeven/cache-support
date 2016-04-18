package sun.support.cache.types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by 264929 on 2016/4/18.
 */
public class ObjectParameterizedType<T extends Type> implements ParameterizedType {
    private T clazz;
    private Type genericType;

    public ObjectParameterizedType(T clazz, Type genericType) {
        this.clazz = clazz;
        this.genericType = genericType;
    }

    @Override
    public Type[] getActualTypeArguments() {
        return new Type[]{genericType};
    }

    @Override
    public Type getRawType() {
        return clazz;
    }

    @Override
    public Type getOwnerType() {
        return null;
    }
}
