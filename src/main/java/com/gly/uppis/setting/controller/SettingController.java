package com.gly.uppis.setting.controller;

import com.gly.uppis.common.response.Response;
import com.gly.uppis.setting.controller.vo.TargetVO;
import com.gly.uppis.setting.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author GuLiyun
 * @date 2019/5/27 21:12
 */
@CrossOrigin
@RestController
@RequestMapping("setting")
public class SettingController {
    @Autowired
    private SettingService settingService;

    @GetMapping("/getTargets")
    public Response getTargets() {
        return Response.ok(settingService.getAllTarget());
    }

    @PostMapping("/saveTargets")
    public Response saveTarget(@RequestBody TargetVO param) {
        settingService.saveTarget(param);
        return Response.ok();
    }
}
