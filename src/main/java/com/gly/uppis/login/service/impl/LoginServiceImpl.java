package com.gly.uppis.login.service.impl;

import com.gly.uppis.common.cache.RedisHelper;
import com.gly.uppis.common.entity.User;
import com.gly.uppis.common.exception.ValidException;
import com.gly.uppis.login.dao.LoginDao;
import com.gly.uppis.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author GuLiyun
 * @date 2019/5/22 14:57
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginDao loginDao;

    @Override
    public void login(String username, String password) {
        User user = loginDao.selectByUserId(username);
        if (user == null) {
            throw new ValidException("该账户不存在");
        }
        if (!user.getPassword().equals(password)) {
            throw new ValidException("密码错误");
        }
        RedisHelper.getRedisUtil().set("userId", String.valueOf(user.getUserId()));
    }
}
