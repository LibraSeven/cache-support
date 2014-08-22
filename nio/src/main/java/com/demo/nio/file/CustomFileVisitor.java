package com.demo.nio.file;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by louis on 2014/8/22.
 */
public class CustomFileVisitor extends SimpleFileVisitor<Path> {
    private PathMatcher matcher = null;
    public CustomFileVisitor(String glob) {
        matcher = FileSystems.getDefault().getPathMatcher("glob:" + glob);
    }
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Path path=file.getFileName();// note this code
        if (matcher.matches(path)) {
            System.out.println(file.getFileName());
        }
        return FileVisitResult.CONTINUE;
    }
}
