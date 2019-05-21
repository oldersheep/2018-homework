package com.xxx.rmi.rpc;

import java.io.Serializable;

/**
 * @Description
 * @Date 2019/5/20 22:14
 * @Version 1.0
 */
public class RpcRequest implements Serializable {
    private static final long serialVersionUID = -4588976973016903501L;

    private String className;
    private String methodName;
    private Object[] args;
    private String version;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
