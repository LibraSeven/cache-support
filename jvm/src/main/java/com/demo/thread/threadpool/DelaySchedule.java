package com.demo.thread.threadpool;

import javax.security.auth.callback.Callback;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by louis on 2014/10/29.
 */
public class DelaySchedule {

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
        System.out.printf("Main: Starting at: %s\n",new Date());
        for (int i=0; i<5; i++) {
            Task task=new Task("Task "+i);
            executor.schedule(task,i+1 , TimeUnit.SECONDS);
        }
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Main: Ends at: %s\n",new Date());
    }




    static class Task implements Callable<String> {
        private String name;

        private Task(String name) {
            this.name = name;
        }

        @Override
        public String call() throws Exception {
            System.out.printf("%s: Starting at : %s\n",name,new Date());
            return "Hello, world";
        }
    }
}
