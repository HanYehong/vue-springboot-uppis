package com.gly.uppis.attendance.service;

import com.gly.uppis.attendance.controller.request.AttendanceSearchRequest;
import com.gly.uppis.common.entity.Attendance;

import java.util.List;

/**
 * @author HanYehong
 * @date 2019/5/23 22:15
 */
public interface AttendanceService {
    List<Attendance> listAttendanceRecord(AttendanceSearchRequest param);
}
