package com.gly.uppis.mark.dao;

import com.gly.uppis.common.entity.Period;

/**
 * @author GuLiyun
 * @date 2019/5/23 0:48
 */
public interface PeriodDao {
    Period selectLastOne();
}
