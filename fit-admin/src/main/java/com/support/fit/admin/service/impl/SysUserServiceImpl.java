package com.support.fit.admin.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.support.fit.admin.mapper.*;
import com.support.fit.admin.service.AppService;
import com.support.fit.admin.service.SysMenuService;
import com.support.fit.admin.service.SysUserService;
import com.support.fit.common.core.api.CommonResult;
import com.support.fit.common.core.constant.enums.MenuTypeEnum;
import com.support.fit.mbg.dto.UserDTO;
import com.support.fit.mbg.dto.UserInfo;
import com.support.fit.mbg.model.SysMenu;
import com.support.fit.mbg.model.SysPost;
import com.support.fit.mbg.model.SysRole;
import com.support.fit.mbg.model.SysUser;
import com.support.fit.mbg.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

    private final AppService appService;

    private final SysRoleMapper sysRoleMapper;

    private final SysDeptMapper sysDeptMapper;

    private final SysMenuService sysMenuService;

    private final SysPostMapper sysPostMapper;

    private final SysUserRoleMapper sysUserRoleMapper;

    private final SysUserPostMapper sysUserPostMapper;

    @Override
    public UserInfo getUserInfo(SysUser sysUser) {
        UserInfo userInfo = new UserInfo();
        userInfo.setSysUser(sysUser);
        // 设置角色列表
        List<SysRole> roleList = sysRoleMapper.listRolesByUserId(sysUser.getUserId());
        userInfo.setRoleList(roleList);
        // 设置角色列表 （ID）
        List<Long> roleIds = roleList.stream().map(SysRole::getRoleId).collect(Collectors.toList());
        userInfo.setRoles(ArrayUtil.toArray(roleIds, Long.class));
        // 设置岗位列表
        List<SysPost> postList = sysPostMapper.listPostsByUserId(sysUser.getUserId());
        userInfo.setPostList(postList);
        // 设置权限列表（menu.permission）
        Set<String> permissions = roleIds.stream()
                .map(sysMenuService::findMenuByRoleId)
                .flatMap(Collection::stream)
                .filter(m -> MenuTypeEnum.BUTTON.getType().equals(m.getType()))
                .map(SysMenu::getPermission)
                .filter(StrUtil::isNotBlank)
                .collect(Collectors.toSet());
        userInfo.setPermissions(ArrayUtil.toArray(permissions, String.class));

        return userInfo;
    }

    @Override
    public IPage<UserVO> getUserWithRolePage(Page page, UserDTO userDTO) {
        return null;
    }

    @Override
    public Boolean removeUserById(SysUser sysUser) {
        return null;
    }

    @Override
    public CommonResult<Boolean> updateUserInfo(UserDTO userDto) {
        return null;
    }

    @Override
    public CommonResult<Boolean> updateUser(UserDTO userDto) {
        return null;
    }

    @Override
    public UserVO getUserVoById(Long id) {
        return null;
    }

    @Override
    public List<SysUser> listAncestorUsersByUsername(String username) {
        return null;
    }

    @Override
    public Boolean saveUser(UserDTO userDto) {
        return null;
    }

    @Override
    public List<Long> listUserIdByDeptIds(Set<Long> deptIds) {
        return null;
    }

    @Override
    public CommonResult<Boolean> registerUser(UserDTO userDto) {
        return null;
    }
}
