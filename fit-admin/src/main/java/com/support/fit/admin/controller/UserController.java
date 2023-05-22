package com.support.fit.admin.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "UserController", description = "后台用户管理")
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
}
