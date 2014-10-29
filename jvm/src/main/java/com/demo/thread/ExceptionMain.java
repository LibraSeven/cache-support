package com.demo.thread;

/**
 * Created by louis on 2014/10/28.
 */
public class ExceptionMain {
    public static void main(String[] args) {
        Task task=new Task();
        Thread thread = new Thread(task);
        thread.setUncaughtExceptionHandler(new ExceptionHandler());
        thread.start();
    }
}
