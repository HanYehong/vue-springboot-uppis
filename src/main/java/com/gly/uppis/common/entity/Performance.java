package com.gly.uppis.common.entity;

public class Performance {
    private Long perId;

    private Integer userId;

    private Integer periodId;

    private Double scoreAttendance;

    private Double scoreTask;

    private Double scoreMark;

    private Double scoreTotal;

    public Long getPerId() {
        return perId;
    }

    public void setPerId(Long perId) {
        this.perId = perId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPeriodId() {
        return periodId;
    }

    public void setPeriodId(Integer periodId) {
        this.periodId = periodId;
    }

    public Double getScoreAttendance() {
        return scoreAttendance;
    }

    public void setScoreAttendance(Double scoreAttendance) {
        this.scoreAttendance = scoreAttendance;
    }

    public Double getScoreTask() {
        return scoreTask;
    }

    public void setScoreTask(Double scoreTask) {
        this.scoreTask = scoreTask;
    }

    public Double getScoreMark() {
        return scoreMark;
    }

    public void setScoreMark(Double scoreMark) {
        this.scoreMark = scoreMark;
    }

    public Double getScoreTotal() {
        return scoreTotal;
    }

    public void setScoreTotal(Double scoreTotal) {
        this.scoreTotal = scoreTotal;
    }
}