package com.xxx.user.services;

import com.xxx.user.IUserCoreService;
import com.xxx.user.constants.ResponseCodeEnum;
import com.xxx.user.dal.entity.User;
import com.xxx.user.dal.persistence.UserMapper;
import com.xxx.user.dto.UserLoginRequest;
import com.xxx.user.dto.UserLoginResponse;
import com.xxx.user.exception.ExceptionUtil;
import com.xxx.user.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service(value = "userCoreService")
public class UserCoreServiceImpl implements IUserCoreService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public UserLoginResponse login(UserLoginRequest request) {
        log.info("login request - > {}", request);
        UserLoginResponse response = new UserLoginResponse();
        try {
            beforeValidate(request);

            User user = userMapper.getUserByUsername(request.getUsername());
            if (user == null || ! request.getPassword().equals(user.getPassword())) {
                response.setCode(ResponseCodeEnum.USERNAME_OR_PASSWORD_ERROR.getCode());
                response.setMsg(ResponseCodeEnum.USERNAME_OR_PASSWORD_ERROR.getMsg());
            } else {
                response.setUid(user.getId());
                response.setMobile(user.getMobile());
                response.setAvatar(user.getAvatar());
                response.setCode(ResponseCodeEnum.SUCCESS.getCode());
                response.setMsg(ResponseCodeEnum.SUCCESS.getMsg());
            }
        } catch (Exception e) {
            ServiceException exception = (ServiceException) ExceptionUtil.handlerException4biz(e);
            response.setCode(exception.getErrorCode());
            response.setMsg(exception.getErrorMessage());
        } finally {
            log.info("login response - > {}", response);
        }

        return response;
    }

    private void beforeValidate(UserLoginRequest request) {
        if (request == null) {

        }
        if (StringUtils.isEmpty(request.getUsername())) {

        }
        if (StringUtils.isEmpty(request.getPassword())) {

        }
    }
}
