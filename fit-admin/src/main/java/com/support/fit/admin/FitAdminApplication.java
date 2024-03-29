package com.support.fit.admin;

import com.support.fit.common.security.annotation.EnableHmsResourceServer;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Slf4j
@EnableHmsResourceServer
@EnableFeignClients(basePackages = "com.support.fit")
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(basePackages = {"com.support.fit.admin.mapper","com.support.fit.mbg.mapper"})
public class FitAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(FitAdminApplication.class, args);
    }
}
