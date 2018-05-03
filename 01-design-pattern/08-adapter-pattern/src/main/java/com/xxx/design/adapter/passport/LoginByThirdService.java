package com.xxx.design.adapter.passport;

import com.xxx.design.adapter.ResultMsg;

public class LoginByThirdService extends LoginService {

    public ResultMsg loginByQQ(String openId) {

        return loginForRegist(openId, null);
    }

    public ResultMsg loginByWechat(String openId) {
        return ResultMsg.ok();
    }

    public ResultMsg loginByTocken(String tocken) {
        return ResultMsg.ok();
    }

    public ResultMsg loginByTelephone(String telephone, String code) {
        return ResultMsg.ok();
    }

    public ResultMsg loginForRegist(String username, String password) {
        super.register(username,password);
        return super.login(username, password);
    }

}
