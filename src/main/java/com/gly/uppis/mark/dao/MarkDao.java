package com.gly.uppis.mark.dao;

import com.gly.uppis.common.entity.Mark;
import org.apache.ibatis.annotations.Param;

/**
 * @author GuLiyun
 * @date 2019/5/23 1:25
 */
public interface MarkDao {
    Mark selectByUserIdAndPeriodId(@Param("userId")Integer userId,
                                   @Param("periodId")Integer periodId);
}
