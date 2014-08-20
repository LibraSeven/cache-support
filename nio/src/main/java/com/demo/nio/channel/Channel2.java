package com.demo.nio.channel;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by louis on 2014/8/20.
 */
public class Channel2 {
    public static void main(String[] args) throws Exception {

        /**
         * Read file using channel simply
         */
        RandomAccessFile file = new RandomAccessFile("pom.xml","r");

        FileChannel fileChannel=file.getChannel();

        ByteBuffer buffer=ByteBuffer.allocate(50);

        while (fileChannel.read(buffer)!=-1) {
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get());
            }
            buffer.clear();
        }

        file.close();
    }
}
