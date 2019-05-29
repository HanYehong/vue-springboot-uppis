package com.gly.uppis.attendance.service.impl;

import com.gly.uppis.attendance.controller.request.AttendanceSearchRequest;
import com.gly.uppis.attendance.dao.AttendanceDao;
import com.gly.uppis.attendance.service.AttendanceService;
import com.gly.uppis.common.entity.Attendance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HanYehong
 * @date 2019/5/23 22:15
 */
@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    private AttendanceDao attendanceDao;

    @Override
    public List<Attendance> listAttendanceRecord(AttendanceSearchRequest param) {
        return attendanceDao.selectByDateAndType(param);
    }
}
