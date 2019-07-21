package com.xxx.nio.buffer;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

public class BufferDemo {

    public static void decode(String str) throws UnsupportedEncodingException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(128);
        byteBuffer.put(str.getBytes("UTF-8"));
        byteBuffer.flip();

        Charset utf8 =  Charset.forName("UTF-8");
        CharBuffer charBuffer = utf8.decode(byteBuffer);
        char[] chars = Arrays.copyOf(charBuffer.array(), byteBuffer.limit());
        System.out.println(chars);
    }

    public static void encode(String str) throws UnsupportedEncodingException {
        CharBuffer charBuffer = CharBuffer.allocate(128);
        charBuffer.append(str);
        charBuffer.flip();

        Charset utf8 =  Charset.forName("UTF-8");
        ByteBuffer byteBuffer = utf8.encode(charBuffer);
        byte[] bytes = Arrays.copyOf(byteBuffer.array(), byteBuffer.limit());
        System.out.println(Arrays.toString(bytes));
    }

    public static void main(String[] args) {
        try {
            decode("中文乱码不");
            encode("中文乱码不");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
