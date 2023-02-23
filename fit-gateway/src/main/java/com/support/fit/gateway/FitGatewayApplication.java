package com.support.fit.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class FitGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(FitGatewayApplication.class, args);
    }
}
