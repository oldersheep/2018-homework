package com.xxx.user.dto;

import com.xxx.user.abs.AbstractResponse;
import lombok.Data;

@Data
public class UserLoginResponse extends AbstractResponse {

    private static final long serialVersionUID = -9081858250299070810L;

    private Integer uid;

    private String avatar;

    private String mobile;
}
