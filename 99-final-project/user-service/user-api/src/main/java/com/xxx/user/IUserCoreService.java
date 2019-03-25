package com.xxx.user;

import com.xxx.user.dto.UserLoginRequest;
import com.xxx.user.dto.UserLoginResponse;

public interface IUserCoreService {

    /**
     * 用户登录操作
     * @param request 用户封装
     * @return
     */
    UserLoginResponse login(UserLoginRequest request);
}
