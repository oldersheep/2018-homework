package com.xxx.user.constants;

public enum ResponseCodeEnum {
    SUCCESS("000000", "成功"),
    USERNAME_OR_PASSWORD_ERROR("001001", "用户名或密码错误"),
    SYSTEM_BUSY("001099","系统繁忙，请稍后重试"),
    SYS_PARAM_NOT_RIGHT("001002", "系统参数异常")
    ;

    private String code;
    private String msg;

    ResponseCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
