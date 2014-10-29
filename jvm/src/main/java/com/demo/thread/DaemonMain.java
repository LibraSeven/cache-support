package com.demo.thread;


import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by louis on 2014/10/28.
 */
public class DaemonMain {
    public static void main(String[] args) {
        Deque<Event> deque = new ArrayDeque<>();
        WriterTask writerTask = new WriterTask(deque);
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(writerTask);
            thread.start();
        }
        CleanerTask cleanerTask = new CleanerTask(deque);
        cleanerTask.start();

    }
}
