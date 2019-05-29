package com.gly.uppis.common.dao;

import com.gly.uppis.common.entity.Target;

import java.util.List;

public interface TargetMapper {
    int deleteByPrimaryKey(Long targetId);

    int insert(Target record);

    int insertSelective(Target record);

    Target selectByPrimaryKey(Long targetId);

    int updateByPrimaryKeySelective(Target record);

    int updateByPrimaryKey(Target record);

    List<Target> selectAll();

    void clearTable();

    void insertTarget(Target target);
}