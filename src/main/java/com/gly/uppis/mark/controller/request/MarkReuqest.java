package com.gly.uppis.mark.controller.request;

/**
 * @author GuLiyun
 * @date 2019/5/23 0:41
 */
public class MarkReuqest {
    private Integer markUserId;

    private Integer type;

    private Double assessment;

    private Integer periodId;

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

    public Double getAssessment() {
        return assessment;
    }

    public void setAssessment(Double assessment) {
        this.assessment = assessment;
    }

    public Integer getPeriodId() {
        return periodId;
    }

    public void setPeriodId(Integer periodId) {
        this.periodId = periodId;
    }
}
