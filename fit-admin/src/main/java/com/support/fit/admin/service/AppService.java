package com.support.fit.admin.service;

import com.support.fit.common.core.api.CommonResult;
import com.support.fit.mbg.dto.AppSmsDTO;

public interface AppService {

    /**
     * 发送手机验证码
     * @param sms phone
     * @return code
     */
    CommonResult<Boolean> sendSmsCode(AppSmsDTO sms);

    /**
     * 校验验证码
     * @param phone 手机号
     * @param code 验证码
     * @return
     */
    boolean check(String phone, String code);
}
