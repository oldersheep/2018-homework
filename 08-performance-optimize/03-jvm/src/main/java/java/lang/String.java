package java.lang;

/**
 * @Description 双亲委派 测试
 * @Date 2019/6/26 21:06
 * @Version 1.0
 */
public class String {

    public static void main(String[] args) {

        /**
         * 错误: 在类 java.lang.String 中找不到 main 方法, 请将 main 方法定义为:
         *    public static void main(String[] args)
         * 否则 JavaFX 应用程序类必须扩展javafx.application.Application
         */
        new String();
    }
}
