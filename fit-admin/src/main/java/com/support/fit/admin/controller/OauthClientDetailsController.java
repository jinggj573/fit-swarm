package com.support.fit.admin.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.support.fit.admin.service.SysOauthClientDetailsService;
import com.support.fit.common.core.api.CommonResult;
import com.support.fit.common.security.annotation.Inner;
import com.support.fit.mbg.model.SysOauthClientDetails;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
@Tag(name = "客户端管理模块")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class OauthClientDetailsController {

    private final SysOauthClientDetailsService sysOauthClientDetailsService;

    @Inner
    @GetMapping("/getClientDetailsById/{clientId}")
    public CommonResult getClientDetailsById(@PathVariable String clientId) {
        return CommonResult.ok(sysOauthClientDetailsService.getOne(
                Wrappers.<SysOauthClientDetails>lambdaQuery().eq(SysOauthClientDetails::getClientId, clientId), false));
    }
}
