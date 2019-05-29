package com.gly.uppis.performance.controller;

import com.gly.uppis.common.response.Response;
import com.gly.uppis.performance.controller.request.PerSelectParamRequest;
import com.gly.uppis.performance.controller.request.UserIdRequest;
import com.gly.uppis.performance.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author GuLiyun
 * @date 2019/5/26 0:43
 */
@CrossOrigin
@RestController
@RequestMapping("performance")
public class PerformanceController {
    @Autowired
    private PerformanceService performanceService;

    @PostMapping("/getLastRecord")
    public Response getLastRecord(@RequestBody UserIdRequest param) {
        return Response.ok(performanceService.getLastRecord(param.getUserId()));
    }

    @PostMapping("/selectData")
    public Response selectData(@RequestBody PerSelectParamRequest param) {
        return Response.ok(performanceService.listPerResult(param.getType(), param.getYear(), param.getUserId()));
    }

    @GetMapping("/getDeptRecord")
    public Response getDeptRecord() {
        return Response.ok(performanceService.getDeptRecord());
    }
}
