package com.demo.jvm;

/**
 * Created by louis on 2014/10/20.
 */
public class ClassLoaderTree {
    public static void main(String[] args) {
        ClassLoader loader=ClassLoaderTree.class.getClassLoader();
        while(loader!=null){
            System.out.println(loader.toString());
            loader=loader.getParent();
        }
    }
}
