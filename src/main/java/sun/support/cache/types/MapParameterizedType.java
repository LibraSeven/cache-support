package sun.support.cache.types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by 264929 on 2016/4/18.
 */
public class MapParameterizedType implements ParameterizedType {
    private Type keyType;
    private Type valueType;

    public MapParameterizedType(Type keyType, Type valueType) {
        this.keyType = keyType;
        this.valueType = valueType;
    }
    @Override
    public Type[] getActualTypeArguments() {
        return new Type[]{keyType, valueType};
    }

    @Override
    public Type getRawType() {
        return Map.class;
    }

    @Override
    public Type getOwnerType() {
        return null;
    }
}
