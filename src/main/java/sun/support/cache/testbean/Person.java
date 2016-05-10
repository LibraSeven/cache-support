package sun.support.cache.testbean;

import sun.support.cache.annotations.Cacheable;
import sun.support.cache.testbean.Foo;

import java.util.List;

/**
 * Created by 264929 on 2016/5/9.
 */
public class Person {
    String name;
    int age;
    List<Foo> list;

    public Person(){}
    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    public Person(String name, int age, List<Foo> list){
        this.name = name;
        this.age = age;
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

    public List<Foo> getList() {
        return list;
    }

    public void setList(List<Foo> list) {
        this.list = list;
    }

    @Cacheable(namespace = "foo_test", fieldsKey = {"#name", "#age"})
    public Foo getFoo(String name, int age){
        return new Foo(name, age);
    }
}
