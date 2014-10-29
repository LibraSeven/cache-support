package com.demo.thread;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by louis on 2014/10/28.
 */
public class FileSearch implements Runnable {
    private String initPath;
    private String fileName;

    public FileSearch(String initPath, String fileName) {
        this.initPath=initPath;
        this.fileName=fileName;
    }
    @Override
    public void run() {
        File file = new File(initPath);
        if (file.isDirectory()) {
            try {
                directoryProcess(file);
            }catch (InterruptedException e){
                System.out.printf("%s:The search has been interrupted\n.",Thread.currentThread().getName());
            }
        }
    }

    private void directoryProcess(File file) throws InterruptedException{
        File list[] = file.listFiles();
        if(list!=null&&list.length>0) {
            for (File f : list) {
                if (f.isDirectory()) {
                    directoryProcess(f);
                }else {
                    if (f.getName().equals(fileName)) {
                        System.out.printf("%s:%s\n",Thread.currentThread().getName(),file.getAbsoluteFile());
                    }
                    if (Thread.interrupted()) {
                        throw new InterruptedException();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        FileSearch search = new FileSearch("D:\\Program Files", "principles.html");
        Thread thread = new Thread(search);
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();

    }
}
