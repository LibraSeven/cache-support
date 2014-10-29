package com.demo.thread.threadpool;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by louis on 2014/10/29.
 */
public class UserValidator {
    private String name;

    public UserValidator(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean validate(String name, String passwd) {
        Random random=new Random();
        try {
            long duration=(long)(Math.random()*10);
            System.out.printf("Validator %s: Validating a user during %d seconds\n",this.name,duration);
                    TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            return false;
        }
        return random.nextBoolean();
    }
}
