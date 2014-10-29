package com.demo.thread.lock;

/**
 * Created by louis on 2014/10/29.
 */
public class Job implements Runnable{
    private PrintQueue printQueue;

    public Job(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }

    @Override
    public void run() {
        System.out.printf("%s: Going to print a document\n", Thread.currentThread().getName());
        printQueue.printJob(new Object());
        System.out.printf("%s: The document has been printed\n", Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        PrintQueue printQueue=new PrintQueue();
        Thread thread[] = new Thread[10];
        for (int i = 0; i < thread.length; i++) {
            thread[i] = new Thread(new Job(printQueue), "Thread_" + i);
        }
        for (Thread aThread : thread) {
            aThread.start();
        }

    }
}
