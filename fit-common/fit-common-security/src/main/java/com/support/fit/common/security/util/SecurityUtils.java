package com.support.fit.common.security.util;

import cn.hutool.core.text.CharSequenceUtil;
import com.support.fit.common.core.constant.SecurityConstants;
import com.support.fit.common.security.service.FitUser;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@UtilityClass
public class SecurityUtils {

    /**
     * 获取Authentication
     */
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取用户
     */
    public FitUser getUser(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof FitUser) {
            return (FitUser) principal;
        }
        return null;
    }

    /**
     * 获取用户
     */
    public FitUser getUser() {
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            return null;
        }
        return getUser(authentication);
    }

    /**
     * 获取用户角色信息
     * @return 角色集合
     */
    public List<Long> getRoles() {
        Authentication authentication = getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        List<Long> roleIds = new ArrayList<>();
        authorities.stream()
                .filter(granted -> CharSequenceUtil.startWith(granted.getAuthority(), SecurityConstants.ROLE))
                .forEach(granted -> {
                    String id = CharSequenceUtil.removePrefix(granted.getAuthority(), SecurityConstants.ROLE);
                    roleIds.add(Long.parseLong(id));
                });
        return roleIds;
    }
}
