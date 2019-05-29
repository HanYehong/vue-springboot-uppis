package com.gly.uppis.performance.service.bo;

import java.util.List;

/**
 * @author GuLiyun
 * @date 2019/5/26 3:34
 */
public class PerResultBO {
    private List<String> xAxis;

    private List<List<Double>> data;

    public List<String> getxAxis() {
        return xAxis;
    }

    public void setxAxis(List<String> xAxis) {
        this.xAxis = xAxis;
    }

    public List<List<Double>> getData() {
        return data;
    }

    public void setData(List<List<Double>> data) {
        this.data = data;
    }
}
