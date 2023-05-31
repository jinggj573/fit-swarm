package com.support.fit.mbg.feign;

import com.support.fit.common.core.api.CommonResult;
import com.support.fit.common.core.constant.ServiceNameConstants;
import com.support.fit.mbg.model.SysLog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(contextId = "remoteLogService",value = ServiceNameConstants.UMPS_SERVICE)
@Component
public interface RemoteLogService {

    /**
     * 保存日志
     * @param sysLog 日志实体
     * @return succes、false
     */
    @PostMapping("/admin/loadByUsername")
    CommonResult<Boolean> saveLog(@RequestBody SysLog sysLog);
}
