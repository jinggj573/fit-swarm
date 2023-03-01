package com.support.fit.mbg.dto;

import lombok.Data;


/**
 * 客户端请求验证码
 *
 * @author lengleng
 * @date 2022/10/13
 */
@Data
public class AppSmsDTO {

	/**
	 * 手机号
	 */
	private String phone;

	/**
	 * 手机号是否存在数据库
	 */
	private Boolean exist;

}
