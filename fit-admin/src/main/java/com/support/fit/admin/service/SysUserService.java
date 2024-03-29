package com.support.fit.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.support.fit.common.core.api.CommonResult;
import com.support.fit.mbg.dto.UserDTO;
import com.support.fit.mbg.dto.UserInfo;
import com.support.fit.mbg.model.SysUser;
import com.support.fit.mbg.vo.UserVO;

import java.util.List;
import java.util.Set;

/**
 * @author lengleng
 * @date 2019/2/1
 */
public interface SysUserService extends IService<SysUser> {

	/**
	 * 查询用户信息
	 * @param sysUser 用户
	 * @return userInfo
	 */
	UserInfo getUserInfo(SysUser sysUser);

	/**
	 * 分页查询用户信息（含有角色信息）
	 * @param page 分页对象
	 * @param userDTO 参数列表
	 * @return
	 */
	IPage<UserVO> getUserWithRolePage(Page page, UserDTO userDTO);

	/**
	 * 删除用户
	 * @param sysUser 用户
	 * @return boolean
	 */
	Boolean removeUserById(SysUser sysUser);

	/**
	 * 更新当前用户基本信息
	 * @param userDto 用户信息
	 * @return Boolean 操作成功返回true,操作失败返回false
	 */
	CommonResult<Boolean> updateUserInfo(UserDTO userDto);

	/**
	 * 更新指定用户信息
	 * @param userDto 用户信息
	 * @return
	 */
	CommonResult<Boolean> updateUser(UserDTO userDto);

	/**
	 * 通过ID查询用户信息
	 * @param id 用户ID
	 * @return 用户信息
	 */
	UserVO getUserVoById(Long id);

	/**
	 * 查询上级部门的用户信息
	 * @param username 用户名
	 * @return R
	 */
	List<SysUser> listAncestorUsersByUsername(String username);

	/**
	 * 保存用户信息
	 * @param userDto DTO 对象
	 * @return success/fail
	 */
	Boolean saveUser(UserDTO userDto);


	/**
	 * 根据部门 id 列表查询对应的用户 id 集合
	 * @param deptIds 部门 id 列表
	 * @return userIdList
	 */
	List<Long> listUserIdByDeptIds(Set<Long> deptIds);

	/**
	 * 注册用户
	 * @param userDto 用户信息
	 * @return success/false
	 */
	CommonResult<Boolean> registerUser(UserDTO userDto);

}
