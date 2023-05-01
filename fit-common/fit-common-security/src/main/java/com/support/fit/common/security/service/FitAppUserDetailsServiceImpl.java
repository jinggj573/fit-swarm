package com.support.fit.common.security.service;

import com.support.fit.admin.feign.RemoteUserService;
import com.support.fit.common.core.api.CommonResult;
import com.support.fit.common.core.constant.CacheConstants;
import com.support.fit.common.core.constant.SecurityConstants;
import com.support.fit.mbg.dto.UserInfo;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 用户详细信息
 *
 */
@Slf4j
@RequiredArgsConstructor
public class FitAppUserDetailsServiceImpl implements FitUserDetailsService {

    private final RemoteUserService remoteUserService;

    private final CacheManager cacheManager;

    /**
     * 手机号登录
     * @param phone 手机号
     * @return
     */
    @Override
    @SneakyThrows
    public UserDetails loadUserByUsername(String phone) {
        Cache cache = cacheManager.getCache(CacheConstants.USER_DETAILS);
        if (cache != null && cache.get(phone) != null) {
            return (FitUser) cache.get(phone).get();
        }

        CommonResult<UserInfo> result = remoteUserService.infoByMobile(phone);

        UserDetails userDetails = getUserDetails(result);
        if (cache != null) {
            cache.put(phone, userDetails);
        }
        return userDetails;
    }

    /**
     * check-token 使用
     * @param pigUser user
     * @return
     */
    @Override
    public UserDetails loadUserByUser(FitUser pigUser) {
        return this.loadUserByUsername(pigUser.getPhone());
    }

    /**
     * 是否支持此客户端校验
     * @param clientId 目标客户端
     * @return true/false
     */
    @Override
    public boolean support(String clientId, String grantType) {
        return SecurityConstants.APP.equals(grantType);
    }

}
