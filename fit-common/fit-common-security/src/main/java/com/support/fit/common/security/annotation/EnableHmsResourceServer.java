package com.support.fit.common.security.annotation;

import com.support.fit.common.security.component.FitResourceServerAutoConfiguration;
import com.support.fit.common.security.component.FitResourceServerConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import java.lang.annotation.*;

/**
 * @author Administrator
 */
@Documented
@Inherited
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({ FitResourceServerAutoConfiguration.class, FitResourceServerConfiguration.class })
public @interface EnableHmsResourceServer {

}
