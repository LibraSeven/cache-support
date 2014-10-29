package com.demo.thread;

import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

/**
 * Created by louis on 2014/10/28.
 */
public class WriterTask implements Runnable {
    private Deque<Event> deque;
    public WriterTask(Deque<Event> deque){
        this.deque=deque;
    }
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            String msg = String.format("The thread %s has generated an event.", Thread.currentThread().getId());
            Event event=new Event(msg,new Date());
            deque.addFirst(event);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
