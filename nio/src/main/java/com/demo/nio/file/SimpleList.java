package com.demo.nio.file;

import java.io.IOException;
import java.nio.file.*;

/**
 * Created by louis on 2014/8/22.
 */
public class SimpleList {
    public static void main(String[] args) throws IOException {

        Iterable<Path> dirs= FileSystems.getDefault().getRootDirectories();
        for (Path name : dirs) {
            System.out.println(name);
        }

        Path newDir=FileSystems.getDefault().getPath("E:\\test\\test_1");
        if(Files.notExists(newDir)){
            Files.createDirectories(newDir);
        }
        // Listing the entire content
        System.out.println("No filter applied.");
        Path path = Paths.get("D:\\github");
        if (Files.isDirectory(path)) {
            try (DirectoryStream<Path> ds = Files.newDirectoryStream(path)) {
                for (Path file : ds) {
                    System.out.println(file.getFileName());
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        Path path2 = Paths.get("D:\\soft");
        if(Files.isDirectory(path2)) {
            try (DirectoryStream<Path> ds = Files.newDirectoryStream(path2, "*.{exe,zip}")) {
                for (Path file : ds) {
                    System.out.println(file.getFileName());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
