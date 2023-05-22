package com.support.fit.auth.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 获取RSA公钥接口
 * Created by macro on 2020/6/19.
 */
@RestController
@Api(tags = "KeyPairController", description = "获取RSA公钥接口")
@RequestMapping("/rsa")
public class KeyPairController {

    /*@Autowired
    private KeyPair keyPair;

    @GetMapping("/publicKey")
    public Map<String, Object> getKey() {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey).build();
        return new JWKSet(key).toJSONObject();
    }*/

}
