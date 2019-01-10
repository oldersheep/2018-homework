package com.xxx.design.decorator.passport.old;

public interface ILoginService {

    ResultMsg register(String username, String password);

    ResultMsg login(String username, String password);

}
