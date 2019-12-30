package com.xxx.concurrent.chapter1;

/**
 * 客户端请求的数据封装
 *
 * @author old boy
 * @version 1.0.0
 * @since 1.0.0
 */
public class Request {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Request{" +
                "name='" + name + '\'' +
                '}';
    }
}
