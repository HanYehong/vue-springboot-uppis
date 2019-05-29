package com.gly.uppis.common.entity;

public class Mark {
    private Long markId;

    private Integer userId;

    private Integer periodId;

    private Double selfAssessment;

    private Double peerAssessment;

    private Double superiorAssessment;

    public Long getMarkId() {
        return markId;
    }

    public void setMarkId(Long markId) {
        this.markId = markId;
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

    public Double getSelfAssessment() {
        return selfAssessment;
    }

    public void setSelfAssessment(Double selfAssessment) {
        this.selfAssessment = selfAssessment;
    }

    public Double getPeerAssessment() {
        return peerAssessment;
    }

    public void setPeerAssessment(Double peerAssessment) {
        this.peerAssessment = peerAssessment;
    }

    public Double getSuperiorAssessment() {
        return superiorAssessment;
    }

    public void setSuperiorAssessment(Double superiorAssessment) {
        this.superiorAssessment = superiorAssessment;
    }
}