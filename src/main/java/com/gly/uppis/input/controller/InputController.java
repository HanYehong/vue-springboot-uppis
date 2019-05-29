package com.gly.uppis.input.controller;

import com.gly.uppis.common.entity.Dept;
import com.gly.uppis.common.entity.User;
import com.gly.uppis.common.response.Response;
import com.gly.uppis.input.service.InputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author GuLiyun
 * @date 2019/5/22 0:20
 */
@CrossOrigin
@RestController
@RequestMapping("input")
public class InputController {
    @Autowired
    private InputService inputService;

    @PostMapping("/user")
    public Response addNewUser(@RequestBody @Validated User user) {
        inputService.addNewUser(user);
        return Response.ok();
    }

    @PostMapping("/dept")
    public Response addNewDept(@RequestBody @Validated Dept dept) {
        inputService.addNewDept(dept);
        return Response.ok();
    }
}
