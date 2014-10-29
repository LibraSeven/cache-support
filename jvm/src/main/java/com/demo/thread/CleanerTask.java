package com.demo.thread;

import java.util.Date;
import java.util.Deque;

/**
 * Created by louis on 2014/10/28.
 */
public class CleanerTask extends Thread {
    private Deque<Event> deque;
    public CleanerTask(Deque<Event> deque){
        this.deque=deque;
        setDaemon(true);
    }
    @Override
    public void run() {
        while(true){
            Date date=new Date();
            clean(date);
        }
    }
    private void clean(Date date){
        long diff;
        boolean del=false;
        if(deque.size()>15)
            System.out.println(deque.size()+"====");
        if(deque.size()==0) return;
        do{
            Event e=deque.getLast();
            diff=date.getTime()-e.getDate().getTime();
            if (diff > 10000) {
                System.out.printf("Cleaner:%s\n", e.getEvent());
                deque.removeLast();
                del=true;
            }
        }while(diff>10000);
        if(del){
            System.out.printf("Cleaner:Size of the queue:%d\n",deque.size());
        }
    }
}
