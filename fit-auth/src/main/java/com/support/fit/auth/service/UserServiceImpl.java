package com.support.fit.auth.service;

import com.support.fit.auth.domain.SecurityUser;
import com.support.fit.common.constant.MessageConstant;
import com.support.fit.common.domain.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private AdminService adminService;

    @Autowired
    private HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("MyUserServiceImpl loadUserByUsername method start invoke !!");
        String clientId = request.getParameter("client_id");
        log.info("MyUserServiceImpl loadUserByUsername method get the clientId:{} !!",clientId);
        UserDto userDto = adminService.loadUserByName(username);
        if (userDto==null) {
            log.error("用户名或密码错误!");
            throw new UsernameNotFoundException(MessageConstant.USERNAME_PASSWORD_ERROR);
        }
        userDto.setClientId(clientId);
        SecurityUser securityUser = new SecurityUser(userDto);
        if (!securityUser.isEnabled()) {
            log.error("该账户已被禁用，请联系管理员!");
            throw new DisabledException(MessageConstant.ACCOUNT_DISABLED);
        } else if (!securityUser.isAccountNonLocked()) {
            log.error("该账号已被锁定，请联系管理员!");
            throw new LockedException(MessageConstant.ACCOUNT_LOCKED);
        } else if (!securityUser.isAccountNonExpired()) {
            log.error("该账号已过期，请联系管理员!");
            throw new AccountExpiredException(MessageConstant.ACCOUNT_EXPIRED);
        } else if (!securityUser.isCredentialsNonExpired()) {
            log.error("该账户的登录凭证已过期，请重新登录!");
            throw new CredentialsExpiredException(MessageConstant.CREDENTIALS_EXPIRED);
        }
        return securityUser;
    }
}
