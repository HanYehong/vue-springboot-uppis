package com.gly.uppis.common.dao;

import com.gly.uppis.common.entity.Attendance;

public interface AttendanceMapper {
    int deleteByPrimaryKey(Long attId);

    int insert(Attendance record);

    int insertSelective(Attendance record);

    Attendance selectByPrimaryKey(Long attId);

    int updateByPrimaryKeySelective(Attendance record);

    int updateByPrimaryKey(Attendance record);
}