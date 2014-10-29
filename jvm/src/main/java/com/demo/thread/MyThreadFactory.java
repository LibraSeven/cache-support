package com.demo.thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadFactory;

/**
 * Created by louis on 2014/10/28.
 */
public class MyThreadFactory implements ThreadFactory {
    private int counter;
    private String name;
    private List<String> stats;

    public MyThreadFactory(String name) {
        this.counter = 0;
        this.name = name;
        this.stats = new ArrayList<String>();
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, name + "-Thread_" + counter);
        stats.add(String.format("created thread %d with name %s on %s\n", t.getId(), t.getName(), new Date()));
        return t;
    }
    public String getStats(){
        StringBuilder sb=new StringBuilder();
        for (String stat : stats) {
            sb.append(stat);
            sb.append("\n");
        }
        return sb.toString();
    }
}
