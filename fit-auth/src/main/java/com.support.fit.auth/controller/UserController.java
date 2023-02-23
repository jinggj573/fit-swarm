package com.support.fit.auth.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "UserController", description = "用户控制层")
@RestController
public class UserController {


    @ApiOperation("hello world 测试")
    @GetMapping("/hello")
    public String hello() {
        return "测试方法，hello world";
    }
}
