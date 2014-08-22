package com.demo.nio.file;


import java.io.IOException;
import java.nio.file.*;

/**
 * Created by louis on 2014/8/22.
 */
public class Traverse {
    public static void main(String[] args) {
        Path path = Paths.get("D:\\github");
        try {
            Files.walkFileTree(path, new CustomFileVisitor("*.java"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
