package com.demo.nio.file;

import java.io.IOException;
import java.nio.file.*;

/**
 * Created by louis on 2014/8/22.
 */
public class FileCheck {
    public static void main(String[] args) throws IOException {
        Path path = FileSystems.getDefault().getPath("pom.xml");
        boolean pathExist = Files.exists(path, LinkOption.NOFOLLOW_LINKS);
        boolean pathNotExist = Files.notExists(path, LinkOption.NOFOLLOW_LINKS);
        System.out.println(pathExist);
        System.out.println(pathNotExist);

        boolean isReadable = Files.isReadable(path);
        boolean isExecutable = Files.isExecutable(path);
        boolean isWritable = Files.isWritable(path);
        boolean isRegular = Files.isRegularFile(path, LinkOption.NOFOLLOW_LINKS);
        System.out.println(isExecutable+" "+isReadable+" "+isWritable+" "+isRegular);

        Path path2 = FileSystems.getDefault().getPath("pom.xml");
        boolean isSame = Files.isSameFile(path, path2);
        System.out.println(isSame);

    }
}
