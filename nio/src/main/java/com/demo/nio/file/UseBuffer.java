package com.demo.nio.file;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by louis on 2014/8/22.
 */
public class UseBuffer {
    public static void main(String[] args) {
        Path path = Paths.get("D:\\dataplatform.log");
        try (BufferedReader bufferedReader = Files.newBufferedReader(path, Charset.forName("UTF-8"))){
            String str=null;
            while((str=bufferedReader.readLine())!=null)
                System.out.println(str);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
