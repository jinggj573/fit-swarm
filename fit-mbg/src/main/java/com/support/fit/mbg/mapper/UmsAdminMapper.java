package com.support.fit.mbg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.support.fit.mbg.model.UmsAdmin;
import com.support.fit.mbg.model.UmsAdminExample;
import com.support.fit.mbg.model.UmsRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsAdminMapper extends BaseMapper<UmsAdmin> {

    List<UmsAdmin> selectByExample(UmsAdminExample example);

    List<UmsRole> getRoleList(@Param("adminId") Long adminId);
}