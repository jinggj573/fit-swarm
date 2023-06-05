package com.support.fit.mbg.feign;

import com.support.fit.common.core.api.CommonResult;
import com.support.fit.common.core.constant.SecurityConstants;
import com.support.fit.common.core.constant.ServiceNameConstants;
import com.support.fit.mbg.model.SysOauthClientDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(contextId = "remoteClientDetailsService",value = ServiceNameConstants.UMPS_SERVICE)
@Component
public interface RemoteClientDetailsService {

    /**
     * 通过clientId 查询客户端信息
     * @param clientId 用户名
     * @return CommonResult
     */
    @GetMapping(value = "/client/getClientDetailsById/{clientId}", headers = SecurityConstants.HEADER_FROM_IN)
    CommonResult<SysOauthClientDetails> getClientDetailsById(@PathVariable("clientId") String clientId);

    /**
     * 查询全部客户端
     * @return CommonResult
     */
    @GetMapping(value = "/client/list", headers = SecurityConstants.HEADER_FROM_IN)
    CommonResult<List<SysOauthClientDetails>> listClientDetails();
}
