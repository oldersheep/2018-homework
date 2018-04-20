package com.xxx.adapter.service;

import com.xxx.adapter.ResultMsg;
import com.xxx.adapter.entity.User;

public class ThirdLoginService extends LoginService {

    public ResultMsg loginForQQ(String qq) {

        // qq 号码全局唯一，可以当做用户名处理
        // QQ密码默认为空
        // 注册，在原有的系统基础上注册一个用户
        // 调用原来的登录方法
        User user = new User();
        user.setUsername(qq);
        user.setPassword(null);

        super.register(user);

        return super.login(qq,null);

    }

    public ResultMsg loginForWechat(String id) {
        return null;
    }

    public ResultMsg LoginForToken(String token) {
        return null;
    }

}
