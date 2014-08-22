package com.demo.nio.file;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by louis on 2014/8/22.
 */
public class SmallFileRead {
    public static void main(String[] args) {
        /**
         * Just for small file. If the file is too large (bigger than 2GB),then the size of the array cann't be
         * allocated and a OutOfMemory error will be thrown.
         */
        Path path = Paths.get("D:\\dataplatform.log");
        try {
            List<String> list=Files.readAllLines(path, Charset.forName("UTF-8"));
            for (String str : list) {
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
