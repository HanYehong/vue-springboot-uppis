package com.gly.uppis.common.dao;

import com.gly.uppis.common.entity.Performance;

public interface PerformanceMapper {
    int deleteByPrimaryKey(Long perId);

    int insert(Performance record);

    int insertSelective(Performance record);

    Performance selectByPrimaryKey(Long perId);

    int updateByPrimaryKeySelective(Performance record);

    int updateByPrimaryKey(Performance record);
}