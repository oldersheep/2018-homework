package com.xxx.user.abs;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class AbstractResponse implements Serializable {

    private static final long serialVersionUID = 545396260059911300L;

    private String code;
    private String msg;
}
