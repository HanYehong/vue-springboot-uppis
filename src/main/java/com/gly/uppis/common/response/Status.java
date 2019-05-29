package com.gly.uppis.common.response;

/**
 * @author HanYehong
 * @date 2019/5/21 16:35
 */
public enum Status {
    OK(10000, "ok"),
    FAIL(20000, "fail"),
    UN_AUTHORIZE(30000, "身份验证失败");

    private int code;
    private String msg;

    private Status(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}