package com.gly.uppis.mark.service.bo;

import com.gly.uppis.common.entity.User;

import java.util.List;

/**
 * @author GuLiyun
 * @date 2019/5/23 0:14
 */
public class MarkPersonBO {
    private User sel;

    private List<User> sameLevelUsers;

    private List<User> subLevelUsers;

    public List<User> getSameLevelUsers() {
        return sameLevelUsers;
    }

    public void setSameLevelUsers(List<User> sameLevelUsers) {
        this.sameLevelUsers = sameLevelUsers;
    }

    public List<User> getSubLevelUsers() {
        return subLevelUsers;
    }

    public void setSubLevelUsers(List<User> subLevelUsers) {
        this.subLevelUsers = subLevelUsers;
    }

    public User getSel() {
        return sel;
    }

    public void setSel(User sel) {
        this.sel = sel;
    }
}
