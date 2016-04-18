package sun.support.cache.redis;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.support.cache.Boo;
import sun.support.cache.Foo;
import sun.support.cache.StringCache;
import sun.support.cache.utils.TestBOO;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by yamorn on 2016/4/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context.xml")
public class RedisStringCacheTest {

    @Autowired
    private StringCache<Object> stringCache;

    private String key = "p_test";

    @Autowired
    private TestBOO testBOO;

    @Test
    public void testSet() throws Exception {
        Person p = new Person("tom", 12);
        p.setList(Arrays.asList("a", "b", "c"));

        stringCache.set(key, p);
    }


    @Test
    public void testSetMap() throws Exception {
        Map<String, Person> map = new HashMap<>();
        map.put("a", new Person("a", 1));
        map.put("b", new Person("b", 2));
        map.put("c", new Person("c", 3));
        stringCache.set(key, map, String.class, Person.class);
    }

    @Test
    public void testGetMap() throws Exception {
        Map<String, Person> map = stringCache.get(key, Map.class, String.class, Person.class);
        for (Map.Entry<String, Person> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue().getName() + " " + entry.getValue().getAge());
        }
    }

    @Test
    public void testGet() throws Exception {
        Person person = stringCache.get(key, Person.class);
        assertNotNull(person);
        System.out.println(person.getName());
    }

    @Test
    public void testSetCollection() throws Exception {
        List<Person> list = new ArrayList<>();
        list.add(new Person("a", 1));
        list.add(new Person("b", 2));
        list.add(new Person("c", 3));
        stringCache.set(key, list, Person.class);
    }

    @Test
    public void testSetGeneric() throws Exception {
        Boo<Foo> boo = new Boo<>("ff", 23, new Foo("china", 132));
        stringCache.set(key, boo, Foo.class);

    }

    @Test
    public void testGetGeneric() throws Exception{
        Boo<Foo> boo = stringCache.get(key, Boo.class, Foo.class);
        System.out.println(boo.getType().getAddress());
    }

    @Test
    public void testSetNull() throws Exception{
        Person p = new Person("t", 2);
        stringCache.set(key, p);
        Person z = stringCache.get(key, Person.class);

    }


    @Test
    public void testGetCollection() throws Exception {
        List<Person> list = stringCache.get(key, List.class, Person.class);
        assertNotNull(list);
        for (Person p : list) {
            System.out.println(p.getName() + " " + p.getAge());
        }
    }

    @Test
    public void testAop(){
        Foo foo = testBOO.getPerson("zhangsan", 23);
        assertNotNull(foo);
    }

    @Test
    public void testAopList(){
        List<Foo> list = testBOO.getList();
        assertNotNull(list);
        for (Foo foo : list) {
            System.out.println(foo.getAddress() + "  " + foo.getNum());
        }
    }

    class Person {
        String name;
        int age;
        List<String> list = new LinkedList<>();

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public List<String> getList() {
            return list;
        }

        public void setList(List<String> list) {
            this.list = list;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}