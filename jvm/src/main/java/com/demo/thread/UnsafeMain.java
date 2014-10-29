package com.demo.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by louis on 2014/10/28.
 */
public class UnsafeMain {
    public static void main(String[] args) {
//        UnsafeTask task=new UnsafeTask();
        SafeTask task=new SafeTask();
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(task);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
