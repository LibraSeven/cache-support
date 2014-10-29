package com.demo.thread.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by louis on 2014/10/29.
 */
public class FactorialCalculator implements Callable<Integer> {
    private Integer number;

    public FactorialCalculator(Integer number) {
        this.number = number;
    }

    @Override
    public Integer call() throws Exception {
        int result = 1;
        if ((number == 0) || (number == 1)) {
            result = 1;
        } else {
            for (int i = 2; i <= number; i++) {
                result *= i;
                TimeUnit.MILLISECONDS.sleep(20);
            }
        }
        System.out.printf("%s: %d\n", Thread.currentThread().getName(), result);
        return result;
    }

    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        List<Future<Integer>> resultList = new ArrayList();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Integer num = random.nextInt(10);
            FactorialCalculator calculator = new FactorialCalculator(num);
            Future<Integer> result = executor.submit(calculator);
            resultList.add(result);
        }
        //Create a do loop to monitor the status of the executor.
        do {
            System.out.printf("Main: Number of Completed Tasks:%d\n", executor.getCompletedTaskCount());
            for (int i = 0; i < resultList.size(); i++) {
                Future<Integer> result = resultList.get(i);
                System.out.printf("Main: Task %d: %s\n", i, result.isDone());
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } while (executor.getCompletedTaskCount() < resultList.size());

        System.out.printf("Main: Results\n");
        for (Future<Integer> result : resultList) {
            Integer number = null;
            try {
                number = result.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            System.out.printf("Main: Task : %d\n", number);
        }
        executor.shutdown();
    }
}
