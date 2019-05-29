package com.gly.uppis.login.controller;

import com.gly.uppis.common.response.Response;
import com.gly.uppis.login.controller.request.LoginParamRequest;
import com.gly.uppis.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author GuLiyun
 * @date 2019/5/22 14:56
 */
@CrossOrigin
@RestController
@RequestMapping("user")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/check")
    public Response checkUser(@RequestBody LoginParamRequest param) {
        loginService.login(param.getUsername(), param.getPassword());
        return Response.ok();
    }
}
