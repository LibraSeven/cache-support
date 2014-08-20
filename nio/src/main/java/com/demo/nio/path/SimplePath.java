package com.demo.nio.path;

import java.io.File;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by louis on 2014/8/20.
 */
public class SimplePath {
    public static void main(String[] args) {
        Path path = Paths.get("pom.xml");
        System.out.println(path.getFileName());
        path = Paths.get(URI.create("file:///C:/Windows/win.ini"));
        System.out.println(path.toAbsolutePath().getFileName());

        path = FileSystems.getDefault().getPath("pom.xml");
        System.out.println(path.toAbsolutePath());

        for(Path p:path.toAbsolutePath()){
            System.out.println(p);
        }
        File file = path.toFile();
        System.out.println(file.exists());


    }
}
