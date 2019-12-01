package com.xxx.spring.servlet;

import com.xxx.spring.annotation.Autowired;
import com.xxx.spring.annotation.Controller;
import com.xxx.spring.annotation.Service;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;

public class DispatchServlet extends HttpServlet {

    private Properties properties = new Properties();
    private Map<String, Object> beanDefinitionMap = new HashMap<>();
    private List<String> beanDefinitionNames = new ArrayList<>(256);
    private final ClassLoader classLoader = this.getClass().getClassLoader();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Post方法执行=====");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 定位
        doLocation(config.getInitParameter("contextConfigLocation"));

        // 加载
        doLoad(properties.getProperty("scanPackage"));

        // 注册
        doRegistry();

        // 自动依赖注入
        // 在spring中 使用 getBean 触发依赖注入
        doAutowired();

        //Spring MVC 会多一个HandlerMapping
        // 将@RequestMapping中的url映射到方法
        initHandlerMapping();


    }

    private void doLocation(String location) {
        // 在Spring中通过Reader进行查找定位
        // 这里就读出来对应的位置即可
        location = location.replace("classpath:", "");
        try (InputStream is = classLoader.getResourceAsStream(location)) {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doLoad(String packageName) {
        URL url = classLoader.getResource("/" + packageName.replaceAll("\\.", "/"));

        File classDir = new File(url.getFile());
        for (File file : classDir.listFiles()) {
            if (file.isDirectory()) {

                doLoad(packageName + "." + file.getName());
            } else {

                beanDefinitionNames.add(packageName + "." + file.getName().replace(".class", ""));
            }
        }
    }

    private void doRegistry() {

        if (beanDefinitionNames.isEmpty()) {
            return;
        }
        try {
            for (String beanDefinitionName : beanDefinitionNames) {
                Class<?> clazz = Class.forName(beanDefinitionName);
                if (clazz.isAnnotationPresent(Controller.class)) {
                    // spring中的value是一个BeanDefinition
                    Object instance = clazz.newInstance();
                    beanDefinitionMap.put(lowerFirstCase(clazz.getSimpleName()), instance);
                } else if (clazz.isAnnotationPresent(Service.class)) {
                    // 当是Service注解的时候，需要考虑 value 的值是否为空
                    // 如果为空，就使用首字母小写的方式进行注入
                    // 否则按value值注入
                    Service service = clazz.getAnnotation(Service.class);
                    String beanName = service.value();
                    if ("".equalsIgnoreCase(beanName)) {
                        beanName = lowerFirstCase(clazz.getSimpleName());
                    }
                    Object instance = clazz.newInstance();
                    beanDefinitionMap.put(beanName, instance);

                    Class<?>[] interfaces = clazz.getInterfaces();
                    for (Class i : interfaces) {
                        beanDefinitionMap.putIfAbsent(i.getName(), instance);
                    }

                } else {
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doAutowired() {

        if (beanDefinitionMap.isEmpty()) {
            return;
        }
        try {
            for (Map.Entry<String, Object> entry : beanDefinitionMap.entrySet()) {
                Field[] fields = entry.getValue().getClass().getDeclaredFields();
                for (Field field : fields) {
                    if (!field.isAnnotationPresent(Autowired.class)){
                        continue;
                    }
                    Autowired autowired = field.getAnnotation(Autowired.class);

                    String beanName = autowired.value().trim();
                    if ("".equals(beanName)) {
                        beanName = field.getType().getName();
                    }
                    field.setAccessible(true);

                    field.set(entry.getValue(), beanDefinitionMap.get(beanName));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initHandlerMapping() {

    }

    private String lowerFirstCase(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }
}
