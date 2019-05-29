package com.gly.uppis.team.service.impl;

import com.gly.uppis.team.controller.request.SearchTeamRequest;
import com.gly.uppis.team.dao.TeamMapper;
import com.gly.uppis.team.dao.result.TeamResult;
import com.gly.uppis.team.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author GuLiyun
 * @date 2019/5/21 16:27
 */
@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamMapper teamMapper;

    @Override
    public List<TeamResult> listTeamUsersByParam(SearchTeamRequest param) {
        return teamMapper.selectByDeptAndName(param);
    }
}
