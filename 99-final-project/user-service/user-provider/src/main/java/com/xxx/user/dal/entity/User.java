package com.xxx.user.dal.entity;

import lombok.Data;

@Data
public class User {


    private Integer id;
    private String username;
    private String password;
    private String realName;
    private String avatar;
    private String mobile;
    private String sex;
    private int status;
    private String createTime;

}
