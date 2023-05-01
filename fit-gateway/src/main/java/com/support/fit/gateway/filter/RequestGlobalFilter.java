package com.support.fit.gateway.filter;

import com.support.fit.common.core.constant.CommonConstants;
import com.support.fit.common.core.constant.SecurityConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collections;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;

/**
 * 全局拦截器，作用所有的微服务 清洗form参数
 * @author Administrator
 */
@Slf4j
public class RequestGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("RequestGlobalFilter method start invoke !!!");
        //清洗请求头中的from参数
        ServerHttpRequest request = exchange.getRequest().mutate().headers(httpHeaders -> {
            httpHeaders.remove(SecurityConstants.FROM);
            httpHeaders.put(CommonConstants.REQUEST_START_TIME,
                    Collections.singletonList(String.valueOf(System.currentTimeMillis())));
        }).build();

        //重写StripPrefix
        ServerWebExchangeUtils.addOriginalRequestUrl(exchange,request.getURI());
        String rawPath = request.getURI().getRawPath();
        log.info("RequestGlobalFilter method get rawPath is:{}:",rawPath);
        //TODO 全局StripPrefix=1配置 不需要
        /*String newPath ="/"+ Arrays.stream(StringUtils.tokenizeToStringArray(rawPath,"/")).skip(1L)
                .collect(Collectors.joining("/"));*/
        String newPath =rawPath;

        log.info("RequestGlobalFilter get the rawPath ===>:{},newPath====>:{}",rawPath,newPath);
        ServerHttpRequest newRequest = exchange.getRequest().mutate().path(newPath).build();
        exchange.getAttributes().put(GATEWAY_REQUEST_URL_ATTR,newRequest.getURI());
        return chain.filter(exchange.mutate().request(newRequest.mutate().build()).build());
    }

    @Override
    public int getOrder() {
        return 10;
    }
}
