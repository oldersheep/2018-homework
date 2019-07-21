package com.xxx.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description -Xmx10m -Xms1m -XX:+HeapDumpOnOutOfMemoryError
 * @Date 2019/6/26 22:03
 * @Version 1.0
 */
public class JVMTest01 {
    byte[] bytes = new byte[1 * 1024 * 1024];

    public static void main(String[] args) {
        List<JVMTest01> list = new ArrayList<>();
        int count = 0;
        try {
            while (true) {
                list.add(new JVMTest01());
                count++;
            }
        } catch (Exception e) {
            System.out.println(count);
            e.printStackTrace();
        }
    }
}
