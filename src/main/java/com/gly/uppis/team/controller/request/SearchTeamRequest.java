package com.gly.uppis.team.controller.request;

import java.util.List;

/**
 * @author GuLiyun
 * @date 2019/5/21 15:29
 */
public class SearchTeamRequest {
    private List<Integer> deptId;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getDeptId() {
        return deptId;
    }

    public void setDeptId(List<Integer> deptId) {
        this.deptId = deptId;
    }
}
