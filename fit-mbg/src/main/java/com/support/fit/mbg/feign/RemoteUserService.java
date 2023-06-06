package com.support.fit.mbg.feign;

import com.support.fit.common.core.api.CommonResult;
import com.support.fit.common.core.constant.SecurityConstants;
import com.support.fit.common.core.constant.ServiceNameConstants;
import com.support.fit.mbg.dto.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Component
@FeignClient(contextId = "remoteUserService",value = ServiceNameConstants.UMPS_SERVICE)
public interface RemoteUserService {
    /**
     * 通过手机号码查询用户、角色信息
     * @param phone 手机号码
     * @return CommonResult
     */
    @GetMapping(value = "/app/info/{phone}",headers = SecurityConstants.HEADER_FROM_IN)
    CommonResult<UserInfo> infoByMobile(@PathVariable("phone") String phone);

    /**
     * 通过用户名查询用户、角色信息
     * @param username 用户名
     * @return CommonResult
     */
    @GetMapping(value = "/user/info/{username}", headers = SecurityConstants.HEADER_FROM_IN)
    CommonResult<UserInfo> info(@PathVariable("username") String username);
}
