package com.demo.thread;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by louis on 2014/10/28.
 */
public class DataSourcesLoader  implements Runnable {
    @Override
    public void run() {
        System.out.printf("Beginning data source loading:%s\n",new Date());
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Data source loading has finished:%s\n",new Date());
    }
}
