package com.demo.thread.mulconditionlock;

import java.util.LinkedList;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by louis on 2014/10/29.
 */
public class Buffer {
    private LinkedList<String> buffer;//store share data
    private int maxSize;// the length of buffer
    private ReentrantLock lock; // controls the access to the blocks of code that modify the buffer
    private Condition lines;
    private Condition space;
    private boolean pendingLines;// indicate if there are lines in the buffer
    public Buffer(int maxSize){
        this.maxSize=maxSize;
        buffer=new LinkedList<>();
        lock=new ReentrantLock();
        lines=lock.newCondition();
        space=lock.newCondition();
        pendingLines=true;
    }

}
