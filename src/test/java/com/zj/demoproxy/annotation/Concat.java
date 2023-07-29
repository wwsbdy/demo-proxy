package com.zj.demoproxy.annotation;

import java.lang.annotation.*;

/**
 * 这是自定义的注解
 * 拼接入参值返回
 * 只能返回String类型
 *
 * @author 19242
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Concat {
}
