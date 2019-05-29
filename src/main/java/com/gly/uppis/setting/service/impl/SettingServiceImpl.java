package com.gly.uppis.setting.service.impl;

import com.gly.uppis.common.dao.TargetMapper;
import com.gly.uppis.common.entity.Target;
import com.gly.uppis.setting.controller.vo.TargetVO;
import com.gly.uppis.setting.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author HanYehong
 * @date 2019/5/27 21:14
 */
@Service
public class SettingServiceImpl implements SettingService {
    @Autowired
    private TargetMapper targetMapper;

    @Override
    public TargetVO getAllTarget() {
        List<Target> targets = targetMapper.selectAll();
        List<Target> highLeader = targets.stream().filter(x -> x.getProperty() == 2).collect(Collectors.toList());
        List<Target> middleLeader = targets.stream().filter(x -> x.getProperty() == 1).collect(Collectors.toList());
        List<Target> user = targets.stream().filter(x -> x.getProperty() == 0).collect(Collectors.toList());
        TargetVO targetVO = new TargetVO();
        targetVO.setHighLeader(highLeader.stream().map(Target::getTargetContent).collect(Collectors.toList()));
        targetVO.setMiddleLeader(middleLeader.stream().map(Target::getTargetContent).collect(Collectors.toList()));
        targetVO.setUser(user.stream().map(Target::getTargetContent).collect(Collectors.toList()));
        return targetVO;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveTarget(TargetVO param) {
        targetMapper.clearTable();
        List<Target> targetList = new ArrayList<>();
        List<String> high = param.getHighLeader();
        for (String s : high) {
            Target target = new Target();
            target.setProperty(2);
            target.setTargetContent(s);
            targetList.add(target);
        }
        List<String> middle = param.getMiddleLeader();
        for (String s : middle) {
            Target target = new Target();
            target.setProperty(1);
            target.setTargetContent(s);
            targetList.add(target);
        }
        List<String> user = param.getUser();
        for (String s : user) {
            Target target = new Target();
            target.setProperty(0);
            target.setTargetContent(s);
            targetList.add(target);
        }
        targetList.forEach(x -> targetMapper.insertTarget(x));
    }
}
