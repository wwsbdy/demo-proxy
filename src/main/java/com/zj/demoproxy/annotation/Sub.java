package com.zj.demoproxy.annotation;

import java.lang.annotation.*;

/**
 * 测试方法注解
 * 入参相减
 * @author 19242
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Sub {
}
