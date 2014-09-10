package com.demo.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by louis on 2014/9/10.
 */

// JVM Args: -Xms100m -Xmx100m -XX:+UseSerialGC
// 作用：以64kb/50毫秒的速度往java堆中填充数据，以共填充1000次
public class MonitoringTest {
    static class OOMObject{
        public byte[] placeholder = new byte[64 * 1024];
    }
    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();
        for(int i=0;i<num;i++){
            Thread.sleep(50);
            list.add(new OOMObject());
        }
//        System.gc();
    }

    public static void main(String[] args) throws InterruptedException {
        fillHeap(1000);
        System.gc();
        System.out.println("");
    }

}
