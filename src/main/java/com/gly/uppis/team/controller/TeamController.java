package com.gly.uppis.team.controller;

import com.gly.uppis.common.response.Response;
import com.gly.uppis.team.controller.request.SearchTeamRequest;
import com.gly.uppis.team.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author GuLiyun
 * @date 2019/5/21 11:18
 */
@CrossOrigin
@RestController
@RequestMapping("team")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @PostMapping("/search")
    public Response search(@RequestBody SearchTeamRequest param) {
        return Response.ok(teamService.listTeamUsersByParam(param));
    }

    @GetMapping("/testSearch")
    public Response testSearch() {
        return Response.ok(1);
    }
}
