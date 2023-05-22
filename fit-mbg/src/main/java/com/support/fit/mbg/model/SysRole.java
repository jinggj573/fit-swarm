package com.support.fit.mbg.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.support.fit.common.mybatis.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRole extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@TableId(value = "role_id", type = IdType.ASSIGN_ID)
	private Long roleId;

	private String roleName;

	private String roleCode;

	private String roleDesc;

	/**
	 * 删除标识（0-正常,1-删除）
	 */
	@TableLogic
	private String delFlag;

}
