package com.gly.uppis.common.entity;

import java.util.Date;

public class MarkDetail {
    private Long id;

    private Integer userId;

    private Integer markUserId;

    private Integer type;

    private Double assessment;

    private Date createTime;

    private Integer periodId;

    public Double getAssessment() {
        return assessment;
    }

    public void setAssessment(Double assessment) {
        this.assessment = assessment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMarkUserId() {
        return markUserId;
    }

    public void setMarkUserId(Integer markUserId) {
        this.markUserId = markUserId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getPeriodId() {
        return periodId;
    }

    public void setPeriodId(Integer periodId) {
        this.periodId = periodId;
    }
}