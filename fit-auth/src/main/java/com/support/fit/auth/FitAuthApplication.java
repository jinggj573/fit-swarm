package com.support.fit.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients(basePackages = "com.support.fit")
@EnableDiscoveryClient
@SpringBootApplication
public class FitAuthApplication  {

    public static void main(String[] args) {
        SpringApplication.run(FitAuthApplication.class, args);
    }
}
