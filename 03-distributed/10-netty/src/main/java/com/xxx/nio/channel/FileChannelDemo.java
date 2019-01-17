package com.xxx.nio.channel;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileChannelDemo {

    public static void main(String[] args) {

        // 写数据
        try {
            // 创建文件
            File file = new File("f:/abc.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            FileChannel fileChannel = fos.getChannel();

            // 通过ByteBuffer将数据写入文件
            ByteBuffer byteBuffer = ByteBuffer.allocate(128);
            byteBuffer.put("中文乱码吧？".getBytes());
            byteBuffer.flip();
            fileChannel.write(byteBuffer);

            // 关闭相应的资源
            byteBuffer.clear();
            fos.close();
            fileChannel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            // 指定读取的文件路径，获取到FileChannel
            Path path = Paths.get("f:/abc.txt");
            FileChannel fileChannel = FileChannel.open(path);
            ByteBuffer byteBuffer = ByteBuffer.allocate((int)fileChannel.size() + 1);

            Charset utf8 = Charset.forName("UTF-8");

            // 将channel中的内容写入到ByteBuffer
            fileChannel.read(byteBuffer);
            byteBuffer.flip();
            CharBuffer charBuffer = utf8.decode(byteBuffer);

            // 输出
            System.out.println(charBuffer.toString());

            // 关闭相应的资源
            byteBuffer.clear();
            charBuffer.clear();
            fileChannel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
