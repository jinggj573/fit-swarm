package com.support.fit.auth.service;

import com.support.fit.common.domain.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("fit-admin")
public interface AdminService {

    @GetMapping("/admin/loadByUsername")
    UserDto loadUserByName(@RequestParam String username);
}
