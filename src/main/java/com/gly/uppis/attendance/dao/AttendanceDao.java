package com.gly.uppis.attendance.dao;

import com.gly.uppis.attendance.controller.request.AttendanceSearchRequest;
import com.gly.uppis.common.entity.Attendance;

import java.util.List;

/**
 * @author GuLiyun
 * @date 2019/5/23 22:17
 */
public interface AttendanceDao {
    List<Attendance> selectByDateAndType(AttendanceSearchRequest param);
}
