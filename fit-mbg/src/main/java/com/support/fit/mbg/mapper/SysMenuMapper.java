package com.support.fit.mbg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.support.fit.mbg.model.SysMenu;

import java.util.Set;


/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

	/**
	 * 通过角色编号查询菜单
	 * @param roleId 角色ID
	 * @return
	 */
	Set<SysMenu> listMenusByRoleId(Long roleId);

}
