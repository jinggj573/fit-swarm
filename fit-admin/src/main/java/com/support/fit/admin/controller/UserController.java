package com.support.fit.admin.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.support.fit.admin.service.SysUserService;
import com.support.fit.common.core.api.CommonResult;
import com.support.fit.common.core.exception.ErrorCodes;
import com.support.fit.common.core.util.MsgUtils;
import com.support.fit.common.security.annotation.Inner;
import com.support.fit.mbg.dto.UserInfo;
import com.support.fit.mbg.model.SysUser;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "UserController", description = "后台用户管理")
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final SysUserService userService;

    @Inner
    @GetMapping("/info/{username}")
    public CommonResult<UserInfo> info(@PathVariable String username) {
        SysUser user = userService.getOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getUsername, username));
        if (user == null) {
            return CommonResult.failed(MsgUtils.getMessage(ErrorCodes.SYS_USER_USERINFO_EMPTY, username));
        }
        return CommonResult.ok(userService.getUserInfo(user));
    }
}
