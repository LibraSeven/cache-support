package com.demo.nio.buffer;

import java.nio.ByteBuffer;

/**
 * Created by louis on 2014/8/20.
 */
public class Compact {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put("hello".getBytes());
        buffer.flip();
        System.out.println(buffer);
        System.out.println((char) buffer.get());
        System.out.println((char)buffer.get());
        System.out.println(buffer);
        /**
         * Can use a buffer in this way as a First In First Out(FIFO) queue. but this is not a very efficient way.
         */
        buffer.compact();

        System.out.println((char)buffer.get());
        System.out.println(buffer);
    }
}
