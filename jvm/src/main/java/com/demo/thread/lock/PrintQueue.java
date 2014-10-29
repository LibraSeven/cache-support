package com.demo.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by louis on 2014/10/29.
 */
public class PrintQueue {
    private final Lock queueLock=new ReentrantLock();

    public void printJob(Object document) {
        // Get control of the Lock object
        queueLock.lock();
        try {
            Long duration = (long) (Math.random() * 10000);
            System.out.println(Thread.currentThread().getName() + ":" +
                    "PrintQueue:Printing a Job during " + (duration / 1000) + " seconds");
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            queueLock.unlock();
        }
    }
}
