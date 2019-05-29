package com.gly.uppis.team.dao.result;

import com.gly.uppis.common.entity.User;

/**
 * @author GuLiyun
 * @date 2019/5/21 15:26
 */
public class TeamResult extends User {
    private String deptName;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
