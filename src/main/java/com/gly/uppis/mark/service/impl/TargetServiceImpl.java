package com.gly.uppis.mark.service.impl;

import com.gly.uppis.common.entity.Target;
import com.gly.uppis.common.entity.User;
import com.gly.uppis.mark.dao.TargetDao;
import com.gly.uppis.mark.dao.UserDao;
import com.gly.uppis.mark.service.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HanYehong
 * @date 2019/5/26 23:50
 */
@Service
public class TargetServiceImpl implements TargetService {
    @Autowired
    private TargetDao targetDao;

    @Autowired
    private UserDao userDao;

    private static Integer USER_ID = 10012001;

    @Override
    public List<Target> selectTargetByProperty(Integer userId) {
        User currentMarkUser = userDao.selectByUserId(userId);
        return targetDao.selectByProperty(currentMarkUser.getLevel());
    }
}
