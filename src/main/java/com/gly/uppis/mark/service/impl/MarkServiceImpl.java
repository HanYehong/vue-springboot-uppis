package com.gly.uppis.mark.service.impl;

import com.gly.uppis.common.dao.DeptMapper;
import com.gly.uppis.common.dao.MarkDetailMapper;
import com.gly.uppis.common.dao.MarkMapper;
import com.gly.uppis.common.entity.*;
import com.gly.uppis.common.exception.ValidException;
import com.gly.uppis.mark.controller.request.MarkReuqest;
import com.gly.uppis.mark.dao.*;
import com.gly.uppis.mark.dao.result.UserWithLeaderResult;
import com.gly.uppis.mark.service.MarkService;
import com.gly.uppis.mark.service.bo.MarkPersonBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author HanYehong
 * @date 2019/5/22 16:57
 */
@Service
public class MarkServiceImpl implements MarkService{
    @Autowired
    private MarkDetailDao markDetailDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private DeptDao deptDao;

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private MarkDetailMapper markDetailMapper;

    @Autowired
    private MarkMapper markMapper;

    @Autowired
    private MarkDao markDao;

    private static Integer USER_ID = 10012001;

    private static Integer PERIOD_ID = 1;

    @Override
    public MarkPersonBO findAllMarkPerson() {
        MarkPersonBO result = new MarkPersonBO();
        // 得到当前用户
        UserWithLeaderResult user = getCurrentUser();
        // 是否完成自评
        Mark mark = markDao.selectByUserIdAndPeriodId(user.getUserId(), PERIOD_ID);
        if (mark == null || mark.getSelfAssessment() == -1) {
            result.setSel(user);
        }
        // 如果非领导，则无下属
        if (!user.getMasterId().equals(user.getUserId())) {
            result.setSameLevelUsers(findAllSameLevelPerson());
            result.setSubLevelUsers(new ArrayList<>());
            return result;
        }
        result.setSameLevelUsers(filterUser(findAllSameLevelPersonForLeader(user)));
        result.setSubLevelUsers(leader(user));
        return result;
    }

    @Override
    public void commitMark(MarkReuqest param) {
        MarkDetail detail = new MarkDetail();
        detail.setUserId(USER_ID);
        detail.setMarkUserId(param.getMarkUserId());
        detail.setPeriodId(param.getPeriodId());
        detail.setType(param.getType());
        detail.setAssessment(param.getAssessment());
        markDetailMapper.insertSelective(detail);
        switch (param.getType()) {
            case 0:
                // 自评 插入新的mark数据
                Mark mark = new Mark();
                mark.setPeriodId(PERIOD_ID);
                mark.setSelfAssessment(param.getAssessment());
                mark.setUserId(USER_ID);
                markMapper.insertSelective(mark);
                break;
            case 1:
                User user = userDao.selectByUserId(param.getMarkUserId());
                // 获得所有同级人员
                List<User> users = findSameLevelForLeader(user);
                // 所有被他人评过的数据
                List<MarkDetail> markDetailList =
                        markDetailDao.selectByMarkUserIdAndPeriodId(param.getMarkUserId(), PERIOD_ID);
                markDetailList = markDetailList.stream().filter(x -> x.getType() == 1).collect(Collectors.toList());
                // 未完成他评阶段
                if (users.size() != markDetailList.size()) {
                    return;
                }
                // 已完成他评阶段，往mark数据表中插入最终他评分值
                // 数据库已存在的mark数据
                Mark dbMark = markDao.selectByUserIdAndPeriodId(param.getMarkUserId(), PERIOD_ID);
                double totalAssessment =
                        markDetailList.stream().mapToDouble(MarkDetail::getAssessment).sum();
                dbMark.setPeerAssessment(markDetailList.size() == 0 ? param.getAssessment() :
                        totalAssessment / markDetailList.size());
                // 更新数据
                markMapper.updateByPrimaryKeySelective(dbMark);
                break;
            case 2:
                // 数据库已存在的mark数据
                Mark dbMark1 = markDao.selectByUserIdAndPeriodId(param.getMarkUserId(), PERIOD_ID);
                // 更新上级评分
                dbMark1.setSuperiorAssessment(param.getAssessment());
                markMapper.updateByPrimaryKeySelective(dbMark1);
                break;
            default:
                break;
        }
    }

    private List<User> findAllSameLevelPerson() {
        List<User> users = filterLeader();
        // 过滤掉被自己评过的人
        users = filterUser(users);
        // 过滤掉目前为止还没有自评的人
        return filterNoSelPeerUp(users, 0);
    }

    private List<User> filterNoSelPeerUp(List<User> userList, int type) {
        userList = userList.stream().filter(x -> {
            Mark mark =
                    markDao.selectByUserIdAndPeriodId(x.getUserId(), PERIOD_ID);
            // 筛选所有已完成自评阶段的人员
            if (type == 0) {
                return mark != null;
            }
            // 筛选所有已完成他评阶段的人员
            if (type == 1) {
                return mark != null && mark.getPeerAssessment() != -1;
            }
            return mark.getSuperiorAssessment() != -1;
        }).collect(Collectors.toList());
        return userList;
    }

    private List<User> filterLeader() {
        // 得到当前用户
        UserWithLeaderResult user = getCurrentUser();
        // 得到所有同部门的人
        List<User> users = userDao.selectByDeptId(user.getDeptId());
        // 过滤掉领导
        int leaderId = user.getMasterId();
        users = users.stream().filter(x -> !x.getUserId().equals(leaderId)).collect(Collectors.toList());
        return users;
    }

    private List<User> findAllSubLevelPerson() {
        // 得到当前用户
        UserWithLeaderResult user = getCurrentUser();
        // 如果非领导，则无下属
        if (!user.getMasterId().equals(user.getUserId())) {
            return new ArrayList<>();
        }
        return leader(user);
    }

    private List<User> findAllSameLevelPersonForLeader(User user) {
        List<User> users = findSameLevelForLeader(user);
        return filterNoSelPeerUp(filterUser(users), 0);
    }

    private List<User> findSameLevelForLeader(User user) {
        Dept dept = deptMapper.selectByPrimaryKey(user.getDeptId());
        List<Dept> peerDepts = deptDao.selectByParentDeptId(dept.getParentDeptId());
        List<Integer> masterIds = peerDepts.stream().map(Dept::getMasterId).collect(Collectors.toList());
        List<User> allMaster = userDao.selectByUserIds(masterIds);
        allMaster = allMaster.stream().
                filter(x -> !x.getUserId().equals(USER_ID)).collect(Collectors.toList());
        return allMaster;
    }

    private List<User> leader(User user) {
        // 判断所在部门是否还有下级部门
        List<Dept> subDepts = deptDao.selectByParentDeptId(user.getDeptId());
        if (CollectionUtils.isEmpty(subDepts)) {
            // 无下级部门，那么下属就为该部门所有员工
            return filterNoSelPeerUp(filterUser(filterLeader()), 1);
        }
        // 有下级部门，那么下属为所有下级部门的主管
        List<Integer> subMasterIds = subDepts.stream().map(Dept::getMasterId).collect(Collectors.toList());
        List<User> subLeaders = userDao.selectByUserIds(subMasterIds);
        return filterNoSelPeerUp(filterUser(subLeaders), 1);
    }

    private UserWithLeaderResult getCurrentUser() {
        UserWithLeaderResult user = userDao.selectByUserId(USER_ID);
        if (user == null) {
            throw new ValidException("获取不到当前登录用户");
        }
        return user;
    }

    private List<User> filterUser(List<User> users) {
        // 得到所有被自己评分过的人
        List<MarkDetail> users1 = markDetailDao.selectByUserIdAndPeriodId(USER_ID, PERIOD_ID);
        // 转化为ID List
        List<Integer> ids = users1.stream().map(MarkDetail::getMarkUserId).collect(Collectors.toList());
        // 筛选出users中所有没有被自己评分过的人
        return users.stream().filter(x -> !ids.contains(x.getUserId())).collect(Collectors.toList());
    }
}
