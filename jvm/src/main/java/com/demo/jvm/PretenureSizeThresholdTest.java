package com.demo.jvm;

/**
 * Created by louis on 2014/9/10.
 */

// JVM Args: -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
public class PretenureSizeThresholdTest {
    private static final int _1MB=1024*1024;
    public static void testPretenureSizeThreshold(){
        byte[] allocation;
        allocation = new byte[4 * _1MB]; // 直接分配在老年代中
    }

    public static void main(String[] args) {
        PretenureSizeThresholdTest.testPretenureSizeThreshold();
    }
}
