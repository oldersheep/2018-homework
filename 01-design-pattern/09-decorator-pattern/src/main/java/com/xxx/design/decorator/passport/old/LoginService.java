package com.xxx.design.decorator.passport.old;

public class LoginService implements ILoginService {
    @Override
    public ResultMsg register(String username, String password) {
        return new ResultMsg(200,"注册成功!");
    }

    @Override
    public ResultMsg login(String username, String password) {
        return new ResultMsg(200,"登录成功!");
    }
}
