package com.gly.uppis.mark.dao;

import com.gly.uppis.common.entity.MarkDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author GuLiyun
 * @date 2019/5/22 17:39
 */
public interface MarkDetailDao {
    List<MarkDetail> selectByUserIdAndPeriodId(@Param("userId")Integer userId,
                                               @Param("periodId")Integer periodId);

    List<MarkDetail> selectByMarkUserIdAndPeriodId(@Param("markUserId")Integer markUserId,
                                               @Param("periodId")Integer periodId);
}
