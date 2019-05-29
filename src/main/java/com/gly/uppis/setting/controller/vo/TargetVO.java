package com.gly.uppis.setting.controller.vo;

import java.util.List;

/**
 * @author HanYehong
 * @date 2019/5/27 21:20
 */
public class TargetVO {
    private List<String> highLeader;

    private List<String> middleLeader;

    private List<String> user;

    public List<String> getHighLeader() {
        return highLeader;
    }

    public void setHighLeader(List<String> highLeader) {
        this.highLeader = highLeader;
    }

    public List<String> getMiddleLeader() {
        return middleLeader;
    }

    public void setMiddleLeader(List<String> middleLeader) {
        this.middleLeader = middleLeader;
    }

    public List<String> getUser() {
        return user;
    }

    public void setUser(List<String> user) {
        this.user = user;
    }
}
