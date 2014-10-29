package com.demo.thread;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by louis on 2014/10/28.
 */
public class FileLock implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.printf("%s\n",new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("The FileLock has been interrupted.\n");
            }
        }
    }

    public static void main(String[] args) {
        FileLock fileLock = new FileLock();
        Thread thread = new Thread(fileLock);
        thread.start();
        for (int i = 0; i < 4; i++) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            thread.interrupt();
        }
    }
}
