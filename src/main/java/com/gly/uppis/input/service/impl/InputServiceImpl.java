package com.gly.uppis.input.service.impl;

import com.gly.uppis.common.dao.DeptMapper;
import com.gly.uppis.common.dao.UserMapper;
import com.gly.uppis.common.entity.Dept;
import com.gly.uppis.common.entity.User;
import com.gly.uppis.input.service.InputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author HanYehong
 * @date 2019/5/22 0:26
 */
@Service
public class InputServiceImpl implements InputService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public void addNewUser(User user) {
        user.setUserId(generateUserId());
        user.setPassword("123456");
        userMapper.insertSelective(user);
    }

    @Override
    public void addNewDept(Dept dept) {
        deptMapper.insertSelective(dept);
    }

    private int generateUserId() {
        StringBuilder str = new StringBuilder();
        Random random=new Random();
        for (int i=0; i<8; i++){
            str.append(random.nextInt(10));
        }
        return Integer.parseInt(str.toString());
    }
}
