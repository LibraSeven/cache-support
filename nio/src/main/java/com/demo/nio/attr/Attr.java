package com.demo.nio.attr;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by louis on 2014/8/20.
 */
public class Attr {
    public static void main(String[] args) throws IOException {

        Path path = Paths.get("pom.xml");

        long size = (Long) Files.getAttribute(path, "basic:size", LinkOption.NOFOLLOW_LINKS);
        //long lastModifiedTime = (Long) Files.getAttribute(path, "basic:lastModifiedTime", LinkOption.NOFOLLOW_LINKS);
        long lastAccessTime = (Long) Files.getAttribute(path, "basic:lastAccessTime", LinkOption.NOFOLLOW_LINKS);
        long creationTime = (Long) Files.getAttribute(path, "basic:creationTime", LinkOption.NOFOLLOW_LINKS);
        boolean isRegularFile = (Boolean) Files.getAttribute(path, "basic:isRegularFile", LinkOption.NOFOLLOW_LINKS);
        boolean isDirectory = (Boolean) Files.getAttribute(path, "basic:isDirectory", LinkOption.NOFOLLOW_LINKS);
        boolean isSymbolicLink = (Boolean) Files.getAttribute(path, "basic:isSymbolicLink", LinkOption.NOFOLLOW_LINKS);
        boolean isOther = (Boolean) Files.getAttribute(path, "basic:isOther", LinkOption.NOFOLLOW_LINKS);
        //fileKey
        System.out.println(String.format("size:%d,creationTime:%d,isRegularFile:%s,isDirectory:%s,isSymbolicLink:%s,isOther:%s",
                size,creationTime,isRegularFile,isDirectory,isSymbolicLink,isOther));

    }
}
