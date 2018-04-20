package com.xxx.adapter.service;

import com.xxx.adapter.ResultMsg;
import com.xxx.adapter.entity.User;

public class LoginService {

    public ResultMsg register(User user) {

        return new ResultMsg(200,"登录成功",user);
    }

    public ResultMsg login(String username, String password) {
        return null;
    }
}
