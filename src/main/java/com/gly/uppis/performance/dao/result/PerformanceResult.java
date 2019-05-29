package com.gly.uppis.performance.dao.result;

import com.gly.uppis.common.entity.Performance;

/**
 * @author HanYehong
 * @date 2019/5/26 1:11
 */
public class PerformanceResult extends Performance {
    private Integer perYear;

    private Integer perMonth;

    private String periodName;

    private Double markWeight;

    private Double taskWeight;

    private Double attendanceWeight;

    public Integer getPerYear() {
        return perYear;
    }

    public void setPerYear(Integer perYear) {
        this.perYear = perYear;
    }

    public Integer getPerMonth() {
        return perMonth;
    }

    public void setPerMonth(Integer perMonth) {
        this.perMonth = perMonth;
    }

    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }

    public Double getMarkWeight() {
        return markWeight;
    }

    public void setMarkWeight(Double markWeight) {
        this.markWeight = markWeight;
    }

    public Double getTaskWeight() {
        return taskWeight;
    }

    public void setTaskWeight(Double taskWeight) {
        this.taskWeight = taskWeight;
    }

    public Double getAttendanceWeight() {
        return attendanceWeight;
    }

    public void setAttendanceWeight(Double attendanceWeight) {
        this.attendanceWeight = attendanceWeight;
    }
}
