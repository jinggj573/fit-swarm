package com.support.fit.admin.controller;

import com.support.fit.admin.service.AdminService;
import com.support.fit.admin.service.impl.MenuServiceImpl;
import com.support.fit.common.core.domain.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "UmsAdminController", description = "后台用户管理")
@RequestMapping("/admin")
@RequiredArgsConstructor
public class UmsAdminController {
    private final AdminService adminService;

    private final MenuServiceImpl menuService;

    @ApiOperation("根据用户名获取通用用户信息")
    @RequestMapping(value = "/loadByUsername", method = RequestMethod.GET)
    @ResponseBody
    public UserDto loadUserByUsername(@RequestParam String username) {
        UserDto userDTO = adminService.loadUserByName(username);
        return userDTO;
    }

    /*@ApiOperation("根据用户名获取通用用户信息")
    @GetMapping
    @ResponseBody
    public R<List<Tree<Long>>> getUserMenu(Long parentId) {
        Set<UmsMenu> menuSet = Arrays.asList(1L).stream().map(menuService::findMenuByRoleId)
                .flatMap(Collection::stream).collect(Collectors.toSet());
        return menuSet;
    }*/


}
