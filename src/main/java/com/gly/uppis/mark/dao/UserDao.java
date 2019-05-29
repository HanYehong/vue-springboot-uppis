package com.gly.uppis.mark.dao;

import com.gly.uppis.common.entity.User;
import com.gly.uppis.mark.dao.result.UserWithLeaderResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author HanYehong
 * @date 2019/5/22 16:58
 */
public interface UserDao {
    List<User> selectByDeptId(@Param("deptId")Integer deptId);

    UserWithLeaderResult selectByUserId(@Param("userId")Integer userId);

    List<User> selectByUserIds(List<Integer> userIds);

//    List<User> selectMarkSubordinate();
}
