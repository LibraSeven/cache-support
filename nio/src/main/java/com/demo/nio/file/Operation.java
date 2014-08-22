package com.demo.nio.file;

import java.io.IOException;
import java.nio.file.*;

/**
 * Created by louis on 2014/8/22.
 */
public class Operation {
    public static void main(String[] args) {

        Path path = Paths.get("D:\\dataplatform.log");

        //delete file

        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //copy file
        Path copyFrom = Paths.get("D:\\httpsqs4j.jar");
        Path copyTo = Paths.get("D:\\test\\httpsqs4j.jar");
        try {
            Files.copy(copyFrom, copyTo);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
