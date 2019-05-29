package com.gly.uppis.mark.dao.result;

import com.gly.uppis.common.entity.User;

/**
 * @author HanYehong
 * @date 2019/5/22 18:28
 */
public class UserWithLeaderResult extends User {
    private String deptName;

    private Integer masterId;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }
}
