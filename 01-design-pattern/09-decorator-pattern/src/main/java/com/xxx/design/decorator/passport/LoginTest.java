package com.xxx.design.decorator.passport;

import com.xxx.design.decorator.passport.old.LoginService;
import com.xxx.design.decorator.passport.upgrede.ILoginForThirdService;
import com.xxx.design.decorator.passport.upgrede.LoginForThirdService;

public class LoginTest {

    public static void main(String[] args) {
        ILoginForThirdService service = new LoginForThirdService(new LoginService());

        service.loginByQQ("9898789");
    }
}
