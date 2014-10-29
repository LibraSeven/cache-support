package com.demo.thread;

/**
 * Created by louis on 2014/10/28.
 */
public class Task implements Runnable {
    @Override
    public void run() {
        int num = Integer.parseInt("TTT");
        System.out.printf("Thread ID:%s\n", Thread.currentThread().getId());
        System.out.println("============");
    }
}
