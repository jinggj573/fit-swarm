package com.support.fit.admin.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.support.fit.admin.service.AppService;
import com.support.fit.admin.service.SysUserService;
import com.support.fit.common.core.api.CommonResult;
import com.support.fit.common.core.exception.ErrorCodes;
import com.support.fit.common.core.util.MsgUtils;
import com.support.fit.common.security.annotation.Inner;
import com.support.fit.mbg.dto.AppSmsDTO;
import com.support.fit.mbg.dto.UserInfo;
import com.support.fit.mbg.model.SysUser;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/app")
@Tag(name = "移动端登录模块")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class AppController {

    private final AppService appService;

    private final SysUserService userService;

    /**
     * 发送手机验证码
     * @param sms 请求手机对象
     * @return code
     */
    @Inner(value = false)
    @PostMapping("/sms")
    public CommonResult<Boolean> sendSmsCode(@Valid @RequestBody AppSmsDTO sms) {
        return appService.sendSmsCode(sms);
    }

    /**
     * 获取指定用户全部信息
     * @param phone 手机号
     * @return 用户信息
     */
    @Inner
    @GetMapping("/info/{phone}")
    public CommonResult<UserInfo> infoByMobile(@PathVariable String phone) {
        SysUser user = userService.getOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getPhone, phone));
        if (user == null) {
            return CommonResult.failed(MsgUtils.getMessage(ErrorCodes.SYS_USER_USERINFO_EMPTY, phone));
        }
        return CommonResult.ok(userService.getUserInfo(user));
    }
}
