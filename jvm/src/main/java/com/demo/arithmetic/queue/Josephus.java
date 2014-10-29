package com.demo.arithmetic.queue;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by louis on 2014/10/29.
 */

/*
    Josephus环 游戏
 */
public class Josephus {
    public static Object josephus(Queue<String> q, int k) {
        if(q.isEmpty()) return null;
        while (q.size() > 1) {
            System.out.println(q.toString());
            for (int i = 0; i < k; i++) {
                q.add(q.poll());
            }
            String e=q.poll();//数到第K个数的人退出
            System.out.println("\n\t" + e + "退出");
        }
        return q.poll();
    }

    public static Queue<String> build(String arr[]) {
        Queue<String> q = new LinkedList<>();
        Collections.addAll(q, arr);
        return q;
    }

    public static void main(String[] args) {
        String[] kid = {"Alice", "Bob", "Cindy", "Doug", "Ed",
                "Fred", "Gene", "Hope", "Irene", "Jack",
                "Kim", "Lance", "Mike", "Nancy", "Ollie"};
        System.out.println("最终的幸运者是" + josephus(build(kid), 5));
    }
}
