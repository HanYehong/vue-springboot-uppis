package com.gly.uppis.mark.controller;

import com.gly.uppis.common.response.Response;
import com.gly.uppis.mark.controller.request.MarkReuqest;
import com.gly.uppis.mark.service.MarkService;
import com.gly.uppis.mark.service.PeriodService;
import com.gly.uppis.mark.service.TargetService;
import com.gly.uppis.mark.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author GuLiyun
 * @date 2019/5/22 20:16
 */
@CrossOrigin
@RestController
@RequestMapping("mark")
public class MarkController {
    @Autowired
    private MarkService markService;

    @Autowired
    private PeriodService periodService;

    @Autowired
    private UserService userService;

    @Autowired
    private TargetService targetService;

    @GetMapping("/listPerson")
    public Response listAllMarkPerson() {
        return Response.ok(markService.findAllMarkPerson());
    }

    @PostMapping("/getUserDetail")
    public Response getUserDetail(@RequestBody MarkReuqest param) {
        return Response.ok(userService.getUserDetail(param.getMarkUserId()));
    }

    @GetMapping("/getPeriodInfo")
    public Response getPeriodInfo() {
        return Response.ok(periodService.getPeriodInfo());
    }

    @PostMapping("/commit")
    public Response commitMark(@RequestBody MarkReuqest param) {
        markService.commitMark(param);
        return Response.ok();
    }

    @PostMapping("/listTarget")
    public Response getTargetList(@RequestBody MarkReuqest param) {
        return Response.ok(targetService.selectTargetByProperty(param.getMarkUserId()));
    }
}
