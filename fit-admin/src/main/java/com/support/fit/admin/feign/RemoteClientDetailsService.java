package com.support.fit.admin.feign;

import com.support.fit.common.core.api.CommonResult;
import com.support.fit.common.core.constant.SecurityConstants;
import com.support.fit.mbg.model.SysOauthClientDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("fit-admin")
public interface RemoteClientDetailsService {

    /**
     * 通过clientId 查询客户端信息
     * @param clientId 用户名
     * @return R
     */
    @GetMapping(value = "/client/getClientDetailsById/{clientId}", headers = SecurityConstants.HEADER_FROM_IN)
    CommonResult<SysOauthClientDetails> getClientDetailsById(@PathVariable("clientId") String clientId);

    /**
     * 查询全部客户端
     * @return R
     */
    @GetMapping(value = "/client/list", headers = SecurityConstants.HEADER_FROM_IN)
    CommonResult<List<SysOauthClientDetails>> listClientDetails();
}
