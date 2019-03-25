package com.xxx.spring.framework.context.support;

import com.xxx.spring.framework.beans.BeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BeanDefinitionReader {

    private final static String SCAN_PACKAGE = "scanPackage";
    private Properties config = new Properties();
    private final ClassLoader classLoader = this.getClass().getClassLoader();
    private List<String> registryBeanClasses = new ArrayList<>();

    public BeanDefinitionReader(String... locations) {
        // 在Spring中通过Reader进行查找定位
        // 这里就读出来对应的位置即可
        locations[0] = locations[0].replace("classpath:", "");
        try (InputStream is = classLoader.getResourceAsStream(locations[0])) {
            config.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        doLoad(config.getProperty(SCAN_PACKAGE));
    }

    public List<String> loadDefinitions() {
        return this.registryBeanClasses;
    }

    // 递归加载需要扫描的包下的
    private void doLoad(String packageName) {
        URL url = classLoader.getResource("/" + packageName.replaceAll("\\.", "/"));

        File classDir = new File(url.getFile());
        for (File file : classDir.listFiles()) {
            if (file.isDirectory()) {

                doLoad(packageName + "." + file.getName());
            } else {

                registryBeanClasses.add(packageName + "." + file.getName().replace(".class", ""));
            }
        }
    }

    // 每注册一个class，就封装一个BeanDefinition
    public BeanDefinition registerBean(String className) {

        if (this.registryBeanClasses.contains(className)) {
            BeanDefinition beanDefinition = new BeanDefinition();
            beanDefinition.setBeanClassName(className);
            beanDefinition.setFactoryBeanName(lowerFirstCase(className.substring(className.lastIndexOf(".") + 1)));
            return beanDefinition;
        }

        return null;
    }

    public Properties getConfig() {
        return this.config;
    }

    private String lowerFirstCase(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }
}
