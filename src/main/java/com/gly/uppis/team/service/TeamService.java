package com.gly.uppis.team.service;

import com.gly.uppis.team.controller.request.SearchTeamRequest;
import com.gly.uppis.team.dao.result.TeamResult;

import java.util.List;

/**
 * @author GuLiyun
 * @date 2019/5/21 16:27
 */
public interface TeamService {
    List<TeamResult> listTeamUsersByParam(SearchTeamRequest param);
}
