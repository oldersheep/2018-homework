package com.xxx.micro.user.domain;

/**
 * @Description 用户的 模型API
 * @Date 2019/7/21 20:35
 * @Version 1.0
 */
public class User {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
