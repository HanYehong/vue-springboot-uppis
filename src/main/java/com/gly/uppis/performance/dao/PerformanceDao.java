package com.gly.uppis.performance.dao;

import com.gly.uppis.common.entity.Performance;
import com.gly.uppis.performance.dao.result.PerformanceResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author GuLiyun
 * @date 2019/5/26 0:52
 */
public interface PerformanceDao {
    PerformanceResult selectLastData(@Param("userId")Integer userId);

    List<PerformanceResult> listPerResult(@Param("type") Integer type,
                                          @Param("year") Integer year,
                                          @Param("userId") Integer userId);

    Performance selectByUserIdAndPeriodId(@Param("userId")Integer userId,
                                          @Param("periodId")Integer periodId);
}
