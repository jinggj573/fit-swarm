package com.support.fit.gateway.filter;

import com.support.fit.common.constant.CommonConstants;
import com.support.fit.common.constant.SecurityConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;

/**
 * 全局拦截器，作用所有的微服务 清洗form参数
 */
@Slf4j
public class RequestGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //清洗请求头中的from参数
        ServerHttpRequest request = exchange.getRequest().mutate().headers(httpHeaders -> {
            httpHeaders.remove(SecurityConstants.FROM);
            httpHeaders.put(CommonConstants.REQUEST_START_TIME,
                    Collections.singletonList(String.valueOf(System.currentTimeMillis())));
        }).build();

        //重写StripPrefix
        ServerWebExchangeUtils.addOriginalRequestUrl(exchange,request.getURI());
        String rawPath = request.getURI().getRawPath();
        String newPath ="/"+ Arrays.stream(StringUtils.tokenizeToStringArray(rawPath,"/")).skip(1L)
                .collect(Collectors.joining("/"));
        log.info("RequestGlobalFilter get the rawPath:{},newPath:{}",rawPath,newPath);
        ServerHttpRequest newRequest = exchange.getRequest().mutate().path(newPath).build();
        exchange.getAttributes().put(GATEWAY_REQUEST_URL_ATTR,newRequest.getURI());
        return chain.filter(exchange.mutate().request(newRequest.mutate().build()).build());
    }

    @Override
    public int getOrder() {
        return 10;
    }
}
