package com.support.fit.gateway.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.support.fit.gateway.filter.PasswordDecoderFilter;
import com.support.fit.gateway.filter.RequestGlobalFilter;
import com.support.fit.gateway.filter.SwaggerBasicGatewayFilter;
import com.support.fit.gateway.filter.ValidateCodeGatewayFilter;
import com.support.fit.gateway.handler.GlobalGateWayExceptionHandler;
import com.support.fit.gateway.handler.ImageCodeHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 网关配置
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(GatewayConfigProperties.class)
public class GatewayConfiguration {

    /***
     * 密码解密工具
     */
    @Bean
    public PasswordDecoderFilter puPasswordDecoderFilter (GatewayConfigProperties gatewayConfigProperties){
        return new PasswordDecoderFilter(gatewayConfigProperties);
    }


    @Bean
    public RequestGlobalFilter requestGlobalFilter(){
        return new RequestGlobalFilter();
    }

    @Bean
    @ConditionalOnProperty(name = "swagger.basic.enabled")
    public SwaggerBasicGatewayFilter swaggerBasicGatewayFilter(
            SpringDocConfiguration.SwaggerDocProperties swaggerProperties) {
        return new SwaggerBasicGatewayFilter(swaggerProperties);
    }

    @Bean
    public GlobalGateWayExceptionHandler goGlobalExceptionHandler(ObjectMapper objectMapper){
        return new GlobalGateWayExceptionHandler(objectMapper);
    }

    @Bean
    public ValidateCodeGatewayFilter validateCodeGatewayFilter(GatewayConfigProperties configProperties,
                                                               ObjectMapper objectMapper, RedisTemplate redisTemplate) {
        return new ValidateCodeGatewayFilter(configProperties, objectMapper, redisTemplate);
    }

    @Bean
    public ImageCodeHandler imageCodeHandler(RedisTemplate redisTemplate) {
        return new ImageCodeHandler(redisTemplate);
    }



}
