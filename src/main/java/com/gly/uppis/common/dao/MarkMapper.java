package com.gly.uppis.common.dao;

import com.gly.uppis.common.entity.Mark;

public interface MarkMapper {
    int deleteByPrimaryKey(Long markId);

    int insert(Mark record);

    int insertSelective(Mark record);

    Mark selectByPrimaryKey(Long markId);

    int updateByPrimaryKeySelective(Mark record);

    int updateByPrimaryKey(Mark record);
}