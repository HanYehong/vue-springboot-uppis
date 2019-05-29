package com.gly.uppis.attendance.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author HanYehong
 * @date 2019/5/23 22:31
 */
public class AttendanceSearchRequest {
    private Integer type;

    private String beginDate;

    private String endDate;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
