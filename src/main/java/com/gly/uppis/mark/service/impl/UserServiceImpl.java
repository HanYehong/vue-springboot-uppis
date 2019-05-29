package com.gly.uppis.mark.service.impl;

import com.gly.uppis.common.entity.User;
import com.gly.uppis.mark.dao.UserDao;
import com.gly.uppis.mark.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author HanYehong
 * @date 2019/5/23 1:49
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User getUserDetail(Integer userId) {
        return userDao.selectByUserId(userId);
    }
}
