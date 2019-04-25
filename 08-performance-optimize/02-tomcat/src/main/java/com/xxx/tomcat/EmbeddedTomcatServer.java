package com.xxx.tomcat;

import java.io.File;

public class EmbeddedTomcatServer {

    public static void main(String[] args) {
        String classesPath = System.getProperty("user.dir") + File.separator + "target" + File.separator +"classes";
        System.out.println(classesPath);
    }
}
