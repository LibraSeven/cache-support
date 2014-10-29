package com.demo.thread.threadpool;

/**
 * Created by louis on 2014/10/29.
 */
public class Main {
    public static void main(String[] args) {
        Server server=new Server();
        for (int i=0; i<100; i++){
            Task task=new Task("Task "+i);
            server.executeTask(task);
        }
        server.endServer();
    }
}
