package com.support.fit.admin.feign;

import com.support.fit.common.core.api.CommonResult;
import com.support.fit.common.core.constant.SecurityConstants;
import com.support.fit.mbg.dto.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("fit-admin")
public interface RemoteUserService {
    /**
     * 通过手机号码查询用户、角色信息
     * @param phone 手机号码
     * @return R
     */
    @GetMapping(value = "/app/info/{phone}",headers = SecurityConstants.HEADER_FROM_IN)
    CommonResult<UserInfo> infoByMobile(@PathVariable("phone") String phone);

    /**
     * 通过用户名查询用户、角色信息
     * @param username 用户名
     * @return R
     */
    @GetMapping(value = "/user/info/{username}", headers = SecurityConstants.HEADER_FROM_IN)
    CommonResult<UserInfo> info(@PathVariable("username") String username);
}
