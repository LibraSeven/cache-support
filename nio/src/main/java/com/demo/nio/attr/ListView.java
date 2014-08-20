package com.demo.nio.attr;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Set;

/**
 * Created by louis on 2014/8/20.
 */
public class ListView {
    public static void main(String[] args) throws IOException {
        FileSystem fs= FileSystems.getDefault();
        Set<String> views=fs.supportedFileAttributeViews();
        for (String view : views) {
            System.out.println(view);
        }

        // or check every partition of disk
        for (FileStore store : fs.getFileStores()) {
            boolean supported = store.supportsFileAttributeView(BasicFileAttributeView.class);
            System.out.println(store.name()+" --- "+supported);
        }

        // or

        Path path = Paths.get("pom.xml");
        FileStore fileStore = Files.getFileStore(path);
        boolean supported = fileStore.supportsFileAttributeView(BasicFileAttributeView.class);
        System.out.println(fileStore.name()+" --- "+supported);
        System.out.println(fileStore.name()+" total space:"+fileStore.getTotalSpace()/(1024*1024*1024)+" usable space:"+fileStore.getUsableSpace());

        BasicFileAttributes attr=null;
        attr = Files.readAttributes(path, BasicFileAttributes.class);
        System.out.println("File size:"+attr.size());
        System.out.println("File creation time:" + attr.creationTime());
        System.out.println("File was last accessed at:" + attr.lastAccessTime());
        System.out.println("File was last modified at:" + attr.lastModifiedTime());
        System.out.println("Is directory:" + attr.isDirectory());
        System.out.println("Is regular file:" + attr.isRegularFile());
    }
}
