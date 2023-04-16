package com.support.fit.admin.service;

import com.support.fit.mbg.model.UmsMenu;

import java.util.Set;

/***
 * 菜单相关的
 */
public interface MenuService {

    Set<UmsMenu> findMenuByRoleId(Long roleId);
}
