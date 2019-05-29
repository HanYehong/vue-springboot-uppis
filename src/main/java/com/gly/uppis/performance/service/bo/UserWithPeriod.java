package com.gly.uppis.performance.service.bo;

/**
 * @author HanYehong
 * @date 2019/5/27 3:42
 */
public class UserWithPeriod {
    private String name;

    private Integer userId;

    private String position;

    private Double scoreTotal;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Double getScoreTotal() {
        return scoreTotal;
    }

    public void setScoreTotal(Double scoreTotal) {
        this.scoreTotal = scoreTotal;
    }
}
