package com.gly.uppis.common.response;

import java.util.LinkedList;

/**
 * @author GuLiyun
 * @date 2019/5/21 16:34
 */
public class Response {
    private int code;
    private Object msg;
    private Object data;

    private Response(int code, Object msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private Response(int code, Object msg) {
        this(code, msg, (Object)null);
    }

    private Response() {
    }

    public static Response fail() {
        return ok(Status.FAIL);
    }

    public static Response fail(Object data) {
        return ok(Status.FAIL, data);
    }

    public static Response ok() {
        return new Response(Status.OK.getCode(), Status.OK.getMsg());
    }

    public static Response ok(Object data) {
        return ok(Status.OK, data);
    }

    public static Response ok(Status status) {
        return new Response(status.getCode(), status.getMsg());
    }

    public static Response ok(Status status, String msg) {
        return new Response(status.getCode(), msg);
    }

    public static Response ok(Status status, Object data) {
        return new Response(status.getCode(), status.getMsg(), data);
    }

    public Object getData() {
        return this.data != null ? this.data : new LinkedList();
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getMsg() {
        return this.msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }
}

