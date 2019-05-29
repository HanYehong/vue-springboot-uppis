package com.gly.uppis.performance.service.bo;

import java.util.List;

/**
 * @author GuLiyun
 * @date 2019/5/27 2:48
 */
public class DeptPerResultBO {
    private String periodName;

    private Integer deptUsersNum;

    private Double avgPeriod;

    private Integer a;

    private Integer b;

    private Integer c;

    private Integer d;

    private List<UserWithPeriod> userPerList;

    public List<UserWithPeriod> getUserPerList() {
        return userPerList;
    }

    public void setUserPerList(List<UserWithPeriod> userPerList) {
        this.userPerList = userPerList;
    }

    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }

    public Integer getDeptUsersNum() {
        return deptUsersNum;
    }

    public void setDeptUsersNum(Integer deptUsersNum) {
        this.deptUsersNum = deptUsersNum;
    }

    public Double getAvgPeriod() {
        return avgPeriod;
    }

    public void setAvgPeriod(Double avgPeriod) {
        this.avgPeriod = avgPeriod;
    }

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    public Integer getB() {
        return b;
    }

    public void setB(Integer b) {
        this.b = b;
    }

    public Integer getC() {
        return c;
    }

    public void setC(Integer c) {
        this.c = c;
    }

    public Integer getD() {
        return d;
    }

    public void setD(Integer d) {
        this.d = d;
    }
}
