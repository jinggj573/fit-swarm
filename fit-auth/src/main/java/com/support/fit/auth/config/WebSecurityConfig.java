package com.support.fit.auth.config;

import com.support.fit.auth.service.UserServiceImpl;
import com.support.fit.auth.support.core.FitDaoAuthenticationProvider;
import com.support.fit.auth.support.core.FormIdentityLoginConfigurer;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * SpringSecurity配置
 */
@Configuration
@EnableWebSecurity
@SpringBootConfiguration
public class WebSecurityConfig  {


    /**
     * 引入密码加密类
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserDetailsService userDetailsService(){
        return new UserServiceImpl();
    }


    /**
     * spring security 默认的安全策略
     * @param http security注入点
     * @return SecurityFilterChain
     * @throws Exception
     */
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests -> authorizeRequests.antMatchers("/token/*")
                        .permitAll()// 开放自定义的部分端点
                        .anyRequest()
                        .authenticated())
                .headers()
                .frameOptions()
                .sameOrigin()// 避免iframe同源无法登录
                .and()
                .apply(new FormIdentityLoginConfigurer()); // 表单登录个性化
        // 处理 UsernamePasswordAuthenticationToken
        http.authenticationProvider(new FitDaoAuthenticationProvider());
        return http.build();
    }

    /**
     * 暴露静态资源
     *
     * https://github.com/spring-projects/spring-security/issues/10938
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    @Order(0)
    SecurityFilterChain resources(HttpSecurity http) throws Exception {
        http.requestMatchers((matchers) -> matchers.antMatchers("/actuator/**", "/css/**", "/error"))
                .authorizeHttpRequests((authorize) -> authorize.anyRequest().permitAll())
                .requestCache()
                .disable()
                .securityContext()
                .disable()
                .sessionManagement()
                .disable();
        return http.build();
    }


}
