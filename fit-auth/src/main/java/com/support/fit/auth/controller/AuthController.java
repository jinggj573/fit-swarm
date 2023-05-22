package com.support.fit.auth.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "AuthController", description = "认证中心登录认证")
@RequestMapping("/oauth")
public class AuthController {
    /*@Autowired
    private TokenEndpoint tokenEndpoint;

    @Autowired
    private AuthorizationEndpoint authorizationEndpoint;

    @Autowired
    private AuthorizationCodeServices  authorizationCodeServices;

    @SneakyThrows
    @ApiOperation("Oauth2获取token")
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public CommonResult<Oauth2TokenDto> postAccessToken(HttpServletRequest request,
                                                        @ApiParam("授权模式") @RequestParam String grantType,
                                                        @ApiParam("Oauth2客户端ID") @RequestParam String clientId,
                                                        @ApiParam("Oauth2客户端秘钥") @RequestParam String clientSecret,
                                                        @ApiParam("刷新token") @RequestParam(required = false) String refreshToken,
                                                        @ApiParam("登录用户名") @RequestParam(required = false) String userName,
                                                        @ApiParam("登录密码") @RequestParam(required = false) String passWord) throws HttpRequestMethodNotSupportedException {
        Map<String, String> parameters = new HashMap<>(16);
        parameters.put("grant_type",grantType);
        parameters.put("client_id",clientId);
        parameters.put("client_secret",clientSecret);
        parameters.putIfAbsent("refresh_token",refreshToken);
        parameters.putIfAbsent("username",userName);
        parameters.putIfAbsent("password",passWord);
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(request.getUserPrincipal(), parameters).getBody();
        Oauth2TokenDto oauth2TokenDto = Oauth2TokenDto.builder()
                .token(oAuth2AccessToken.getValue())
                .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                .expiresIn(oAuth2AccessToken.getExpiresIn())
                .tokenHead(AuthConstant.JWT_TOKEN_PREFIX).build();

        return CommonResult.success(oauth2TokenDto);
    }

    /**
     * 授权码模式
     */
    /*@ApiOperation("Oauth2获取token")
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public CommonResult<Oauth2TokenDto> postAccessToken(HttpServletRequest request,
                                                        @ApiParam("授权模式") @RequestParam String grant_type,
                                                        @ApiParam("Oauth2客户端ID") @RequestParam String client_id,
                                                        @ApiParam("Oauth2客户端秘钥") @RequestParam String client_secret,
                                                        @ApiParam("刷新token") @RequestParam(required = false) String refresh_token,
                                                        @ApiParam("授权码") @RequestParam String code,
                                                        @ApiParam("重定向URI") @RequestParam String redirect_uri) throws HttpRequestMethodNotSupportedException {
        Map<String, String> parameters = new HashMap<>(16);
        parameters.put("grant_type",grant_type);
        parameters.put("client_id",client_id);
        parameters.put("client_secret",client_secret);
        parameters.putIfAbsent("refresh_token",refresh_token);
        parameters.putIfAbsent("code",code);
        parameters.putIfAbsent("redirect_uri",redirect_uri);
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(request.getUserPrincipal(), parameters).getBody();
        Oauth2TokenDto oauth2TokenDto = Oauth2TokenDto.builder()
                .token(oAuth2AccessToken.getValue())
                .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                .expiresIn(oAuth2AccessToken.getExpiresIn())
                .tokenHead(AuthConstant.JWT_TOKEN_PREFIX).build();

        return CommonResult.success(oauth2TokenDto);
    }*/


}
