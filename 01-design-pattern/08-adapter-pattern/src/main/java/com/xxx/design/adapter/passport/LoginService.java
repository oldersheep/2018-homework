package com.xxx.design.adapter.passport;

import com.xxx.design.adapter.Member;
import com.xxx.design.adapter.ResultMsg;

public class LoginService {

    public ResultMsg register(String username, String password) {

        return ResultMsg.build(200,"注册成功!", new Member());
    }

    public ResultMsg login(String username, String password) {
        return ResultMsg.ok();
    }
}
