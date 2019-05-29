package com.gly.uppis.performance.service;

import com.gly.uppis.performance.dao.result.PerformanceResult;
import com.gly.uppis.performance.service.bo.DeptPerResultBO;
import com.gly.uppis.performance.service.bo.PerResultBO;

import java.util.List;

/**
 * @author GuLiyun
 * @date 2019/5/26 0:42
 */
public interface PerformanceService {
    PerformanceResult getLastRecord(Integer userId);

    PerResultBO listPerResult(Integer type, Integer years, Integer userId);

    DeptPerResultBO getDeptRecord();
}
