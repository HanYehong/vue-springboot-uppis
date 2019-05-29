package com.gly.uppis.performance.service.impl;

import com.gly.uppis.common.entity.Performance;
import com.gly.uppis.common.entity.Period;
import com.gly.uppis.common.entity.User;
import com.gly.uppis.mark.dao.PeriodDao;
import com.gly.uppis.mark.dao.UserDao;
import com.gly.uppis.performance.dao.PerformanceDao;
import com.gly.uppis.performance.dao.result.PerformanceResult;
import com.gly.uppis.performance.service.PerformanceService;
import com.gly.uppis.performance.service.bo.DeptPerResultBO;
import com.gly.uppis.performance.service.bo.PerResultBO;
import com.gly.uppis.performance.service.bo.UserWithPeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author HanYehong
 * @date 2019/5/26 0:42
 */
@Service
public class PerformanceServiceImpl implements PerformanceService {
    @Autowired
    private PerformanceDao performanceDao;

    @Autowired
    private PeriodDao periodDao;

    @Autowired
    private UserDao userDao;

    private static Integer USER_ID = 10012001;

    @Override
    public PerformanceResult getLastRecord(Integer userId) {
        if (userId == null) {
            userId = USER_ID;
        }
        return performanceDao.selectLastData(userId);
    }

    @Override
    public PerResultBO listPerResult(Integer type, Integer year, Integer userId) {
        if (userId == null) {
            userId = USER_ID;
        }
        List<PerformanceResult> result = performanceDao.listPerResult(type, year, userId);
        PerResultBO perResultBO = new PerResultBO();
        List<List<Double>> data = new ArrayList<>();
        List list1 = result.stream().map(Performance::getScoreTotal).collect(Collectors.toList());
        List list2 = result.stream().map(Performance::getScoreAttendance).collect(Collectors.toList());
        List list3 = result.stream().map(Performance::getScoreTask).collect(Collectors.toList());
        List list4 = result.stream().map(Performance::getScoreMark).collect(Collectors.toList());
        data.add(list1);data.add(list2);data.add(list3);data.add(list4);
        perResultBO.setData(data);
        List<String> name = new ArrayList<>();
        // 月度绩效
        if (type == 0) {
            name = result.stream()
                    .map(x -> x.getPerMonth() + "月").collect(Collectors.toList());
        }
        // 季度绩效
        if (type == 1) {
            name = result.stream()
                    .map(x -> "第" + Math.abs(x.getPerMonth()) + "季度").collect(Collectors.toList());
        }
        // 如果是年度绩效 由于结果是2019、2018、2017……形式  因此要进行反转
        if (type == 2) {
//            Collections.reverse(result);
            name = result.stream()
                    .map(x -> x.getPerYear() + "年").collect(Collectors.toList());
        }
        perResultBO.setxAxis(name);
        return perResultBO;
    }

    @Override
    public DeptPerResultBO getDeptRecord() {
        DeptPerResultBO result = new DeptPerResultBO();
        Period period = periodDao.selectLastOne();
        User user = userDao.selectByUserId(USER_ID);
        List<User> deptUsers = userDao.selectByDeptId(user.getDeptId());
        int levelA = 0, levelB = 0, levelC = 0, levelD = 0;
        double totalPer = 0;
        List<UserWithPeriod> userPerList = new ArrayList<>();
        for (User u : deptUsers) {
            UserWithPeriod userWithPeriod = new UserWithPeriod();
            userWithPeriod.setName(u.getName());
            userWithPeriod.setUserId(u.getUserId());
            userWithPeriod.setPosition(u.getPosition());
            Performance p = performanceDao.selectByUserIdAndPeriodId(u.getUserId(), period.getPeriodId());
            if (p == null) {
                userWithPeriod.setScoreTotal(0.0);
                userPerList.add(userWithPeriod);
                continue;
            }
            totalPer += p.getScoreTotal();
            if (p.getScoreTotal() >= 95) {
                levelA++;
            }
            if (p.getScoreTotal() >= 85 && p.getScoreTotal() < 95) {
                levelB++;
            }
            if (p.getScoreTotal() >= 75 && p.getScoreTotal() < 85) {
                levelC++;
            }
            if (p.getScoreTotal() < 75) {
                levelD++;
            }
            userWithPeriod.setScoreTotal(p.getScoreTotal());
            userPerList.add(userWithPeriod);
        }
        result.setPeriodName(period.getPeriodName());
        result.setDeptUsersNum(deptUsers.size());
        result.setAvgPeriod(deptUsers.size() == 0 ? 0 : totalPer / deptUsers.size());
        result.setA(levelA);
        result.setB(levelB);
        result.setC(levelC);
        result.setD(levelD);
        result.setUserPerList(userPerList);
        return result;
    }
}
