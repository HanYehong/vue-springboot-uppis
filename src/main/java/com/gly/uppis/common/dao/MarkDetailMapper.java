package com.gly.uppis.common.dao;

import com.gly.uppis.common.entity.MarkDetail;

public interface MarkDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MarkDetail record);

    int insertSelective(MarkDetail record);

    MarkDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MarkDetail record);

    int updateByPrimaryKey(MarkDetail record);
}