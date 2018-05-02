package com.xxx.design.proxy.custom;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class MyProxy {

    private static final String ln = "\r\n";

    public static Object newProxyInstance(MyClassLoader classLoader, Class<?>[] interfaces, MyInvocationHandler handler) {

        try {
            // 1、动态生成源代码文件
            String src = generateSrc(interfaces);
            // 2、Java文件输出到磁盘
            String filePath = MyProxy.class.getResource("").getPath();
            System.out.println(filePath);
            File file = new File(filePath + "$Proxy0.java");
            FileWriter writer = new FileWriter(file);
            writer.write(src);
            writer.flush();
            writer.close();

            // 3、把生成的Java文件编译成Class文件
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager = compiler.getStandardFileManager(null,null,null);
            Iterable iterable = manager.getJavaFileObjects(file);
            JavaCompiler.CompilationTask task = compiler.getTask(null,manager,null,null,null,iterable);
            task.call();
            manager.close();

            // 4、编译生成的class文件加载到JVM中
            Class<?> proxyClass = classLoader.findClass("$Proxy0");
            Constructor<?> constructor = proxyClass.getConstructor(MyInvocationHandler.class);

            // 5、返回字节码重组后的新的代理对象
            return constructor.newInstance(handler);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String generateSrc(Class<?>[] interfaces) {

        StringBuffer stringBuffer = new StringBuffer();
        // 包路径
        stringBuffer.append("package com.xxx.design.proxy.custom;").append(ln);
        // 导包
        // stringBuffer.append("import com.xxx.proxy.jdk.Dao;").append(ln);
        stringBuffer.append("import java.lang.reflect.Method;").append(ln);
        // 类开始
        stringBuffer.append("public class $Proxy0 implements " ).append(interfaces[0].getName()).append(ln);
        stringBuffer.append("{").append(ln);
        // MyInvocationHandler成员变量
        stringBuffer.append("private MyInvocationHandler h;").append(ln);
        // 构造方法
        stringBuffer.append("public $Proxy0(MyInvocationHandler h) { this.h = h; }").append(ln);

        for (Method method : interfaces[0].getMethods()) {
            stringBuffer.append("public "+method.getReturnType().getName()+" "+method.getName()+" (){").append(ln);
            stringBuffer.append("try {").append(ln);
            stringBuffer.append("Method m = "+interfaces[0].getName()+".class.getMethod(\""+method.getName()+"\",new Class[]{});").append(ln);
            stringBuffer.append("this.h.invoke(this,m,null);");
            stringBuffer.append("} catch (Throwable e) { e.printStackTrace();}").append(ln);
            stringBuffer.append("}");
        }

        stringBuffer.append("}").append(ln);
        return stringBuffer.toString();
    }
}