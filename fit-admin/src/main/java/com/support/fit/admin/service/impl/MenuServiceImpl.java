package com.support.fit.admin.service.impl;

import com.support.fit.admin.mapper.UmsAdminMenuMapper;
import com.support.fit.admin.service.MenuService;
import com.support.fit.mbg.model.UmsMenu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author jgj
 */
@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private  final UmsAdminMenuMapper menuMapper;

    @Override
    public Set<UmsMenu> findMenuByRoleId(Long roleId) {
        return menuMapper.listMenusByRoleId(roleId);
    }
}
