package com.zj.demoproxy.annotation;

import com.zj.demoproxy.config.register.ImportBeanDefinitionRegister;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 允许自动代理继承BaseTemplate的接口
 *
 * @author arthur_zhou
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@AutoConfigurationPackage
@Import(ImportBeanDefinitionRegister.class)
public @interface EnableAutoProxyInterface {

}

