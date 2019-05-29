package com.gly.uppis.login.controller.request;

/**
 * @author HanYehong
 * @date 2019/5/22 14:59
 */
public class LoginParamRequest {
    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
