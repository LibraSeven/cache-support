package sun.support.cache.types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Set;

/**
 * Created by 264929 on 2016/4/18.
 */
public class SetParameterizedType implements ParameterizedType {

    private Type type;

    public SetParameterizedType(Type type){
        this.type = type;
    }
    @Override
    public Type[] getActualTypeArguments() {
        return new Type[]{type};
    }

    @Override
    public Type getRawType() {
        return Set.class;
    }

    @Override
    public Type getOwnerType() {
        return null;
    }
}
