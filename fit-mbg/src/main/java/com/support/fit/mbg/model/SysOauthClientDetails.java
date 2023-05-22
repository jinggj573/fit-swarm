/*
 * Copyright (c) 2020 pig4cloud Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.support.fit.mbg.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.support.fit.common.mybatis.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * <p>
 * 客户端信息
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysOauthClientDetails extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 客户端ID
	 */
	//@NotBlank(message = "client_id 不能为空")
	@TableId(value = "client_id", type = IdType.INPUT)
	private String clientId;

	/**
	 * 客户端密钥
	 */
	//@NotBlank(message = "client_secret 不能为空")
	private String clientSecret;

	/**
	 * 资源ID
	 */
	private String resourceIds;

	/**
	 * 作用域
	 */
	//@NotBlank(message = "scope 不能为空")
	private String scope;

	/**
	 * 授权方式（A,B,C）
	 */
	private String authorizedGrantTypes;

	/**
	 * 回调地址
	 */
	private String webServerRedirectUri;

	/**
	 * 权限
	 */
	private String authorities;

	/**
	 * 请求令牌有效时间
	 */
	private Integer accessTokenValidity;

	/**
	 * 刷新令牌有效时间
	 */
	private Integer refreshTokenValidity;

	/**
	 * 扩展信息
	 */
	private String additionalInformation;

	/**
	 * 是否自动放行
	 */
	private String autoapprove;

}
