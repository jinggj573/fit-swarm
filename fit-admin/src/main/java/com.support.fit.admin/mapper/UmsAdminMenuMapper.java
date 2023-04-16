package com.support.fit.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.support.fit.mbg.model.UmsMenu;

import java.util.Set;

/**
 * <p>
 * 后台菜单表 Mapper 接口
 * </p>
 *
 * @author young
 * @since 2023-03-20
 */

public interface UmsAdminMenuMapper extends BaseMapper<UmsMenu> {

    /**
     * 通过角色id 查询菜单
     * @param roleId 角色id
     * @return
     */
    Set<UmsMenu> listMenusByRoleId(Long roleId);

}
