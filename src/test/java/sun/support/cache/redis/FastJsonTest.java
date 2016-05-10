package sun.support.cache.redis;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import sun.support.cache.testbean.Foo;
import sun.support.cache.testbean.Person;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by 264929 on 2016/5/10.
 */
public class FastJsonTest {
    @Test
    public void testSerialize(){
        Foo foo1 = new Foo("haha", 34.55);
        Foo foo2 = new Foo("zzzz", 34.55);
        List<Foo> list = new LinkedList<>();
        list.add(foo1);
        list.add(foo2);
        Person person = new Person("tom", 23,list);

        String jsonString = JSON.toJSONString(person);
        Person p = JSON.parseObject(jsonString, Person.class);
        assertNotNull(p);
        System.out.println(p.getList().size());
    }
}
