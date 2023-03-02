package com.support.fit.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan({"com.support.fit.admin.mapper","com.support.fit.mbg.mapper"})

public class FitAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(FitAdminApplication.class, args);
    }
}
