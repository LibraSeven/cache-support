package com.demo.thread;

import java.util.Date;

/**
 * Created by louis on 2014/10/28.
 */
public class Main {
    public static void main(String[] args) {
        DataSourcesLoader dataSourcesLoader=new DataSourcesLoader();
        NetworkConnectionsLoader networkConnectionsLoader=new NetworkConnectionsLoader();
        Thread thread1 = new Thread(dataSourcesLoader);
        Thread thread2 = new Thread(networkConnectionsLoader);
        thread1.start();
        thread2.start();
        try {
            // When calling join method,it suspends the execution of the calling thread unit the object finishes its execution.
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Main:Configuration has been loaded:%s=\n",new Date());
    }
}
