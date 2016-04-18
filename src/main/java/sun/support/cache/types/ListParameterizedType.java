package sun.support.cache.types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 264929 on 2016/4/18.
 */
public class ListParameterizedType implements ParameterizedType {
    private Type type;

    public ListParameterizedType(Type type){
        this.type = type;
    }

    @Override
    public Type[] getActualTypeArguments() {
        return new Type[]{this.type};
    }

    @Override
    public Type getRawType() {
        return List.class;
    }

    @Override
    public Type getOwnerType() {
        return null;
    }
}
