package com.support.fit.mbg.feign;

import com.support.fit.common.core.api.CommonResult;
import com.support.fit.common.core.constant.SecurityConstants;
import com.support.fit.common.core.constant.ServiceNameConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(contextId = "remoteTokenService",value = ServiceNameConstants.AUTH_SERVICE)
@Component
public interface RemoteTokenService {

    /**
     * 分页查询token 信息
     * @param params 分页参数
     * @return page
     */
    @PostMapping(value = "/token/page", headers = SecurityConstants.HEADER_FROM_IN)
    CommonResult<?> getTokenPage(@RequestBody Map<String, Object> params);

    /**
     * 删除token
     * @param token token
     * @return
     */
    @DeleteMapping(value = "/token/{token}", headers = SecurityConstants.HEADER_FROM_IN)
    CommonResult<Boolean> removeToken(@PathVariable("token") String token);
}
