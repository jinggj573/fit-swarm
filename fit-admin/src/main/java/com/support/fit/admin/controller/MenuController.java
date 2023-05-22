package com.support.fit.admin.controller;

import cn.hutool.core.lang.tree.Tree;
import com.support.fit.admin.service.SysMenuService;
import com.support.fit.common.core.api.CommonResult;
import com.support.fit.common.security.util.SecurityUtils;
import com.support.fit.mbg.model.SysMenu;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@Api(tags = "MenuController", description = "后台用户管理")
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {

    private final SysMenuService sysMenuService;

    @GetMapping
    public CommonResult<List<Tree<Long>>> getUserMenu(Long parentId) {
        // 获取符合条件的菜单
        Set<SysMenu> menuSet = SecurityUtils.getRoles().stream().map(sysMenuService::findMenuByRoleId)
                .flatMap(Collection::stream).collect(Collectors.toSet());
        return CommonResult.success(sysMenuService.filterMenu(menuSet, parentId));
    }
}
