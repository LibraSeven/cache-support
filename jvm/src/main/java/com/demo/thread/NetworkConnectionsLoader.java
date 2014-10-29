package com.demo.thread;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by louis on 2014/10/28.
 */
public class NetworkConnectionsLoader implements Runnable {
    @Override
    public void run() {
        System.out.printf("Beginning network data source loading:%s\n",new Date());
        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Data network source loading has finished:\n"+new Date());
    }
}
