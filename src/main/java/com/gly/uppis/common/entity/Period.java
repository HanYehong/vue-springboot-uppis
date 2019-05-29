package com.gly.uppis.common.entity;

import java.util.Date;

public class Period {
    private Integer periodId;

    private Integer perYear;

    private Integer perMonth;

    private String periodName;

    private Double uWeight;

    private Double oWeight;

    private Double mWeight;

    private Double leaderUWeight;

    private Double leaderOWeight;

    private Double markWeight;

    private Double taskWeight;

    private Double attendanceWeight;

    private Date createTime;

    public Integer getPeriodId() {
        return periodId;
    }

    public void setPeriodId(Integer periodId) {
        this.periodId = periodId;
    }

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
        this.periodName = periodName == null ? null : periodName.trim();
    }

    public Double getuWeight() {
        return uWeight;
    }

    public void setuWeight(Double uWeight) {
        this.uWeight = uWeight;
    }

    public Double getoWeight() {
        return oWeight;
    }

    public void setoWeight(Double oWeight) {
        this.oWeight = oWeight;
    }

    public Double getmWeight() {
        return mWeight;
    }

    public void setmWeight(Double mWeight) {
        this.mWeight = mWeight;
    }

    public Double getLeaderUWeight() {
        return leaderUWeight;
    }

    public void setLeaderUWeight(Double leaderUWeight) {
        this.leaderUWeight = leaderUWeight;
    }

    public Double getLeaderOWeight() {
        return leaderOWeight;
    }

    public void setLeaderOWeight(Double leaderOWeight) {
        this.leaderOWeight = leaderOWeight;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}