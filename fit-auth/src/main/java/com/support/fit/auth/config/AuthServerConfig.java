package com.support.fit.auth.config;

import com.support.fit.auth.component.JwtTokenEnhancer;
import com.support.fit.auth.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;

/**
 * OAuth2的认证服务配置
 */
@Configuration
@AllArgsConstructor
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    public DataSource dataSource;
    @Autowired
    private UserServiceImpl userDetailsService;

    // 认证管理器
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenEnhancer jwtTokenEnhancer;

    @Bean
    public ClientDetailsService jdbcClientDetailsService() {
        //将client信息存储在数据库中
        return new JdbcClientDetailsService(dataSource);
    }

    @Bean
    public TokenStore tokenStore() {
        //token默认保存在内存中（也可以保存在数据库、Redis中）。
        //如果保存在中间件（数据库、Redis），那么资源服务器与认证服务器可以不在同一个工程中。
        //注意：如果不保存access_token，则没法通过access_token取得用户信息
        //对token进行持久化存储在数据库中，数据存储在oauth_access_token和oauth_refresh_token
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        //加入对授权码模式的支持
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    /**
     * 配置客户端
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //设置客户端的配置从数据库中读取，存储在oauth_client_details表
        clients.withClientDetails(jdbcClientDetailsService());
    }



    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setKeyPair(keyPair());
        return jwtAccessTokenConverter;
    }

    /**
     * 用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> delegates = new ArrayList<>();
        delegates.add(jwtTokenEnhancer);
        delegates.add(accessTokenConverter());
        enhancerChain.setTokenEnhancers(delegates);

        //token存储方式
        endpoints.tokenStore(tokenStore())
                .userDetailsService(userDetailsService)
                // 开启密码验证，来源于 WebSecurityConfigurerAdapter
                .authenticationManager(authenticationManager)
                .accessTokenConverter(accessTokenConverter())
                .tokenEnhancer(enhancerChain)
                .reuseRefreshTokens(true)
                .authorizationCodeServices(authorizationCodeServices())
                /*// 自定义授权跳转
                .pathMapping("/oauth/confirm_access", "/custom/confirm_access")
                // 自定义异常跳转
                .pathMapping("/oauth/error", "/view/oauth/error")*/
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .setClientDetailsService(jdbcClientDetailsService());
        /*
         * 默认获取token的路径是/oauth/token，通过pathMapping方法，可改变默认路径
         * pathMapping用来配置端点URL链接，有两个参数，都将以 "/" 字符为开始的字符串
         * defaultPath：这个端点URL的默认链接
         * customPath：你要进行替代的URL链接
         */
        //endpoints.pathMapping("/oauth/token", "/oauth/token");
    }

    /**
     * 配置资源服务器向认证服务器请求及验证token的规则：默认允许获取token，但是需要授权后才能获取到
     *过来验令牌有效性的请求，不是谁都能验的，必须要是经过身份认证的。
     * 所谓身份认证就是，必须携带clientId，clientSecret，否则随便一请求过来验token是不验的
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 开启/oauth/token_key 验证端口无权限访问
        security.tokenKeyAccess("permitAll()")
                // 开启/oauth/check_token 验证端口认证权限访问
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients()
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public KeyPair keyPair() {
        //从classpath下的证书中获取秘钥对
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "Tpyl63995@".toCharArray());
        return keyStoreKeyFactory.getKeyPair("jwt", "Tpyl63995@".toCharArray());
    }


}
