package com.demo.jvm;

/**
 * Created by louis on 2014/9/10.
 */

// JVM Args: -XX:+PrintGCDetails        输出GC日志

// 引用计数算法回收内存（java中使用的是分代收集算法)
public class ReferenceCountingGC {
    public Object instance=null;
    private static final int _1MB=1024*1024;
    private byte[] bigSize = new byte[2 * _1MB];
    public static void testGC(){
        ReferenceCountingGC objA=new ReferenceCountingGC();
        ReferenceCountingGC objB=new ReferenceCountingGC();
        objA.instance=objB;
        objB.instance=objA;
        objA=null;
        objB=null;

        System.gc();
    }

    public static void main(String[] args) {
        ReferenceCountingGC.testGC();
    }
}
