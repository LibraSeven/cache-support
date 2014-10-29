package com.demo.thread;

/**
 * Created by louis on 2014/10/28.
 */
public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.printf("An exception has been captured\n");
        System.out.printf("Thread:%s\n", t.getId());
        System.out.printf("Exception:%s:%s\n", e.getClass().getName(), e.getMessage());
        System.out.printf("Stack Trace:\n");
        e.printStackTrace();
        System.out.printf("Thread status:%s\n", t.getState());
    }
}
