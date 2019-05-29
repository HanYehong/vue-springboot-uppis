package com.gly.uppis.performance.controller.request;

/**
 * @author HanYehong
 * @date 2019/5/26 3:52
 */
public class PerSelectParamRequest {
    private Integer type;

    private Integer year;

    private Integer userId;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
