package com.xxx.design.decorator.passport.upgrede;

import com.xxx.design.decorator.passport.old.ILoginService;
import com.xxx.design.decorator.passport.old.ResultMsg;

public class LoginForThirdService implements ILoginForThirdService {

    private ILoginService service;

    public LoginForThirdService(ILoginService service) {
        this.service = service;
    }

    @Override
    public ResultMsg register(String username, String password) {
        return service.register(username, password);
    }

    @Override
    public ResultMsg login(String username, String password) {
        return service.login(username, password);
    }

    @Override
    public ResultMsg loginByQQ(String openId) {
        return loginForRegist(openId, null);
    }

    @Override
    public ResultMsg loginByWechat(String openId) {
        return null;
    }

    @Override
    public ResultMsg loginByTocken(String tocken) {
        return null;
    }

    @Override
    public ResultMsg loginByTelephone(String telephone, String code) {
        return null;
    }

    @Override
    public ResultMsg loginForRegist(String username, String password) {
        this.register(username,password);
        return this.login(username, password);
    }
}
