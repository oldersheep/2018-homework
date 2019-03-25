package com.xxx.user.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginRequest implements Serializable {
    private static final long serialVersionUID = -6890165116723343768L;

    private String username;
    private String password;

}
