package com.support.fit.common.swagger.annotation;

import com.support.fit.common.swagger.config.SwaggerAutoConfiguration;
import com.support.fit.common.swagger.support.SwaggerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启 pig spring doc
 *
 */

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableConfigurationProperties(SwaggerProperties.class)
@Import({ SwaggerAutoConfiguration.class })
public @interface EnableFitDoc {
}
