package com.support.fit.auth.sms;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.support.fit.auth.domain.SecurityUser;
import com.support.fit.auth.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Configuration
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AdminService adminService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthenticationToken smsCodeAuthenticationToken = (SmsCodeAuthenticationToken) authentication;
        String mobile = (String) smsCodeAuthenticationToken.getPrincipal();
        //usersDao = SpringContextUtils.getBean(UsersDao.class);

        //查询手机号的详细信息
        //QueryWrapper<Users> usersQueryWrapper =new QueryWrapper<>();
        //usersQueryWrapper.eq("phone_no",mobile).eq("delete_flag",0);

        //Users user = usersDao.selectOne(usersQueryWrapper);
        SecurityUser user =null;
        if(null!=user){
            //校验手机收到的验证码和rediss中的验证码是否一致
            checkSmsCode(mobile);
            //授权通过
            UserDetails userDetails = buildUserDetails(user);
            return new SmsCodeAuthenticationToken(userDetails, userDetails.getAuthorities());
        }else{
            throw new BadCredentialsException("该手机号未注册或未绑定账号!");
        }
    }

    /**
     * 构建用户认证信息
     * @param user 用户对象
     * @return UserDetails
     */
    private UserDetails buildUserDetails(SecurityUser user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList("ADMIN")) ;
    }

    /**
     * 校验手机号与验证码的绑定关系是否正确
     * 在调用短信验证码认之前我们需要先生成验证码，接口需要自己实现
     * Redis的存储风格按照自己的习惯，能够保证唯一即可
     * 然后根据手机号信息去Redis中查询对应的验证码是否正确
     * @param mobile 手机号码
     */
    private void checkSmsCode(String mobile) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取验证码
        String smsCode = request.getParameter("smsCode");
        //从redis中获取只当key的值
        //String smsStr = JedisUtils.getObject("sms"+mobile);
        String smsStr = null;
        if(StringUtils.isEmpty(smsCode) || !smsCode.equals(smsStr)){
            throw new BadCredentialsException("验证码错误!");
        }
    }

    /**
     * ProviderManager 选择具体Provider时根据此方法判断
     * 判断 authentication 是不是 SmsCodeAuthenticationToken 的子类或子接口
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
