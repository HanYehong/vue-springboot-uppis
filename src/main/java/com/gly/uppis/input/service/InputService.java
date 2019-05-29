package com.gly.uppis.input.service;

import com.gly.uppis.common.entity.Dept;
import com.gly.uppis.common.entity.User;

/**
 * @author HanYehong
 * @date 2019/5/22 0:21
 */
public interface InputService {
    /**
     * 新增员工
     * @param user
     */
    void addNewUser(User user);

    /**
     * 新增部门
     * @param dept
     */
    void addNewDept(Dept dept);
}
