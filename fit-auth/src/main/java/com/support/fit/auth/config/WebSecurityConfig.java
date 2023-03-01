package com.support.fit.auth.config;

import com.support.fit.auth.service.UserServiceImpl;
import com.support.fit.auth.sms.SmsCodeAuthenticationProvider;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * SpringSecurity配置
 */
@Configuration
@EnableWebSecurity
@SpringBootConfiguration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    /**
     * 引入密码加密类
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(providers())
                .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());

    }

    @Override
    @Bean
    public UserDetailsService userDetailsService(){
        return new UserServiceImpl();
    }

    /**
     * 支持密码模式配置
     * @return
     * @throws Exception
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 配置URL访问授权,必须配置authorizeRequests(),否则启动报错,说是没有启用security技术。
     * 注意:在这里的身份进行认证与授权没有涉及到OAuth的技术：当访问要授权的URL时,请求会被DelegatingFilterProxy拦截,
     *      如果还没有授权,请求就会被重定向到登录界面。在登录成功(身份认证并授权)后,请求被重定向至之前访问的URL。
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 必须配置，不然OAuth2的http配置不生效----不明觉厉
                .requestMatchers()
                .antMatchers("/oauth/**", "/login/**", "/logout/**","/captcha/get",
                        "/captcha/check","/code/**")
                .and()
                .authorizeRequests()
                // 自定义页面或处理url是，如果不配置全局允许，浏览器会提示服务器将页面转发多次
                .antMatchers("/auth/login", "/auth/authorize","/code/**")
                .permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/oauth/token").permitAll()
                .anyRequest()
                .authenticated();

        // 表单登录
        http.formLogin();
                // 登录处理url
                //.loginProcessingUrl("/auth/authorize");
        http.httpBasic().disable();

    }


    @Bean
    public SmsCodeAuthenticationProvider providers(){
        SmsCodeAuthenticationProvider provider =new SmsCodeAuthenticationProvider();
        return provider;
    }

}
