package com.gly.uppis.attendance.controller;

import com.gly.uppis.attendance.controller.request.AttendanceSearchRequest;
import com.gly.uppis.attendance.service.AttendanceService;
import com.gly.uppis.common.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HanYehong
 * @date 2019/5/23 22:14
 */
@RestController
@RequestMapping("attend")
public class AttendanceConroller {
    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/search")
    public Response searchAttend(@RequestBody AttendanceSearchRequest param) {
        return Response.ok(attendanceService.listAttendanceRecord(param));
    }
}
