package com.support.fit.admin.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.service.IService;
import com.support.fit.mbg.model.SysMenu;

import java.util.List;
import java.util.Set;

public interface SysMenuService extends IService<SysMenu> {

    /**
     * 通过角色编号查询URL 权限
     * @param roleId 角色ID
     * @return 菜单列表
     */
    Set<SysMenu> findMenuByRoleId(Long roleId);

    /**
     * 查询菜单
     * @param menuSet
     * @param parentId
     * @return
     */
    List<Tree<Long>> filterMenu(Set<SysMenu> menuSet, Long parentId);
}
