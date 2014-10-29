package com.demo.thread.threadpool;

import com.demo.thread.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by louis on 2014/10/29.
 */
public class TaskValidator implements Callable<String> {
    private UserValidator userValidator;
    private String name;
    private String passwd;

    public TaskValidator(UserValidator userValidator, String name, String passwd) {
        this.userValidator = userValidator;
        this.name = name;
        this.passwd = passwd;
    }

    @Override
    public String call() throws Exception {
        if (!userValidator.validate(name, passwd)) {
            System.out.printf("%s: The user has not been found\n",userValidator.getName());
            throw new Exception("Error validating user");
        }
        System.out.printf("%s: The user has been found\n",userValidator.getName());
        return userValidator.getName();
    }

    public static void main(String[] args) {
        String username="test";
        String password="test";
        UserValidator ldapValidator=new UserValidator("LDAP");
        UserValidator dbValidator=new UserValidator("DataBase");
        TaskValidator ldapTask=new TaskValidator(ldapValidator,username, password);
        TaskValidator dbTask=new TaskValidator(dbValidator,username,password);
        List<TaskValidator> taskList=new ArrayList<>();
        taskList.add(ldapTask);
        taskList.add(dbTask);
        ExecutorService executorService= Executors.newCachedThreadPool();
        // process the first result
        /*
        String result;
        try {
            result = executorService.invokeAny(taskList);
            System.out.printf("Main: Result: %s\n",result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        */
        //process all the result
        List<Future<String>> resultList=null;
        try {
            resultList=executorService.invokeAll(taskList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown();

        System.out.println("Main: Printing the results");
        assert resultList != null;
        for (int i = 0; i < resultList.size(); i++) {
            Future<String> future = resultList.get(i);
            try {
                String name=future.get();
                System.out.printf("name of the result:%s\n",name);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Main: End of the Execution\n");
    }
}
