package com.gly.uppis.login.dao;

import com.gly.uppis.common.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author HanYehong
 * @date 2019/5/22 14:58
 */
public interface LoginDao {
    User selectByUserId(@Param("username") String username);
}
