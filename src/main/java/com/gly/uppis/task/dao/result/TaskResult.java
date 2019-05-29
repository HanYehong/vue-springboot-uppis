package com.gly.uppis.task.dao.result;

import com.gly.uppis.common.entity.Task;

/**
 * @author HanYehong
 * @date 2019/5/24 9:45
 */
public class TaskResult extends Task {
    private String name;

    private String deptName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
