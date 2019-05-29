package com.gly.uppis.mark.dao;

import com.gly.uppis.common.entity.Target;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author HanYehong
 * @date 2019/5/26 23:53
 */
public interface TargetDao {
    List<Target> selectByProperty(@Param("property")Integer property);
}
