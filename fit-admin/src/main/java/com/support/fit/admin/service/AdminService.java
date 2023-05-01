package com.support.fit.admin.service;

import com.support.fit.common.core.domain.UserDto;
import com.support.fit.mbg.model.UmsAdmin;
import com.support.fit.mbg.model.UmsRole;

import java.util.List;

public interface AdminService {

    UmsAdmin getAdminByUsername(String username);

    UserDto loadUserByName(String username);

    /**
     * 获取用户对于角色
     */
    List<UmsRole> getRoleList(Long adminId);


}
