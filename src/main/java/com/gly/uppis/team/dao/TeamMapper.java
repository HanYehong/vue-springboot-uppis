package com.gly.uppis.team.dao;

import com.gly.uppis.team.controller.request.SearchTeamRequest;
import com.gly.uppis.team.dao.result.TeamResult;

import java.util.List;

/**
 * @author HanYehong
 * @date 2019/5/21 15:24
 */
public interface TeamMapper {

    List<TeamResult> selectByDeptAndName(SearchTeamRequest param);
}
