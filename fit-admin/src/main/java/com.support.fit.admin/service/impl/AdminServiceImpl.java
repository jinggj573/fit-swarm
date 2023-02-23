package com.support.fit.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.support.fit.admin.mapper.AdminRoleRelationMapper;
import com.support.fit.admin.service.AdminService;
import com.support.fit.common.domain.UserDto;
import com.support.fit.mbg.mapper.UmsAdminMapper;
import com.support.fit.mbg.model.UmsAdmin;
import com.support.fit.mbg.model.UmsAdminExample;
import com.support.fit.mbg.model.UmsRole;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UmsAdminMapper adminMapper;


    @Autowired
    private AdminRoleRelationMapper roleRelationDao;

    @Override
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> adminList = adminMapper.selectByExample(example);
        if (adminList != null && adminList.size() > 0) {
            return adminList.get(0);
        }
        return null;
    }

    @Override
    public UserDto loadUserByName(String username) {
        UmsAdmin umsAdmin = adminMapper.selectOne(new LambdaQueryWrapper<UmsAdmin>()
                .eq(UmsAdmin::getUsername, username));
        if (ObjectUtil.isNotNull(umsAdmin)) {
            List<UmsRole> roleList = getRoleList(umsAdmin.getId());
            UserDto userDTO = new UserDto();
            BeanUtils.copyProperties(umsAdmin,userDTO);
            if(CollUtil.isNotEmpty(roleList)){
                List<String> roleStrList = roleList.stream().map(item -> item.getId() + "_" + item.getName()).collect(Collectors.toList());
                userDTO.setRoles(roleStrList);
            }
            return userDTO;
        }
        return null;
    }

    @Override
    public List<UmsRole> getRoleList(Long adminId) {
        return adminMapper.getRoleList(adminId);
    }
}
