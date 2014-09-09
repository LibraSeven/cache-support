package com.demo.nio.file;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by louis on 2014/9/9.
 */
public class MappedFile {
    public void readFile1(File file){
        long start=System.currentTimeMillis();
        RandomAccessFile rFile = null;
        FileChannel fileChannel=null;
        try {
            rFile = new RandomAccessFile(file, "rw");
            fileChannel=rFile.getChannel();
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, fileChannel.size());
            byte[] buffer=new byte[1024*5];
            while (mappedByteBuffer.hasRemaining()&&buffer.length<mappedByteBuffer.remaining()) {
                mappedByteBuffer.get(buffer);
                //System.out.print(new String(buffer, 0, buffer.length, "utf-8"));
            }
            //read the remained bytes less than buffer's size
            byte[] lastBuffer=new byte[mappedByteBuffer.remaining()];
            mappedByteBuffer.get(lastBuffer);
            //System.out.print(new String(lastBuffer,"utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(fileChannel!=null){
                try {
                    fileChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(rFile!=null){
                try {
                    rFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        long end=System.currentTimeMillis();
        System.out.println("\nCost time:"+(end-start));
    }

    public void readFile2(File file) {
        long start=System.currentTimeMillis();
        BufferedInputStream bufferedInputStream=null;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer=new byte[1024*5];
            int p=-1;
            while ((p=bufferedInputStream.read(buffer) )!= -1) {
                //System.out.print(new String(buffer, 0, p, "utf-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(bufferedInputStream!=null){
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        long end=System.currentTimeMillis();
        System.out.println("\nCost time:"+(end-start));

    }
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\TPano_1024shenyang.txt");
        MappedFile mappedFile=new MappedFile();
//        mappedFile.readFile1(file); // 30ms
        mappedFile.readFile2(file); // 129ms

    }
}
