package sun.support.cache.utils;

import org.springframework.stereotype.Component;
import sun.support.cache.Boo;
import sun.support.cache.Foo;
import sun.support.cache.annotations.Cacheable;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by yamorn on 2016/4/18.
 */
@Component
public class TestBOO {

    @Cacheable(namespace = "hw", fieldsKey = {"#name", "#age"})
    public Foo getPerson(String name, Integer age){
        return new Foo(name, age);
    }

    @Cacheable(namespace = "hw", fieldsKey = {}, genericType = {Foo.class})
    public List<Foo> getList(){
        List<Foo> list = new LinkedList<>();
        list.add(new Foo("zz", 3));
        list.add(new Foo("tt", 4));
        list.add(new Foo("gg", 5));
        return list;
    }
}
