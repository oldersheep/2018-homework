package com.xxx.design.decorator.passport.upgrede;

import com.xxx.design.decorator.passport.old.ILoginService;
import com.xxx.design.decorator.passport.old.ResultMsg;

public interface ILoginForThirdService extends ILoginService {

    ResultMsg loginByQQ(String openId);

    ResultMsg loginByWechat(String openId);

    ResultMsg loginByTocken(String tocken);

    ResultMsg loginByTelephone(String telephone, String code);

    ResultMsg loginForRegist(String username, String password);
}
