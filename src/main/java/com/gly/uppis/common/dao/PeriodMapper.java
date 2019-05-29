package com.gly.uppis.common.dao;

import com.gly.uppis.common.entity.Period;

public interface PeriodMapper {
    int deleteByPrimaryKey(Integer periodId);

    int insert(Period record);

    int insertSelective(Period record);

    Period selectByPrimaryKey(Integer periodId);

    int updateByPrimaryKeySelective(Period record);

    int updateByPrimaryKey(Period record);
}