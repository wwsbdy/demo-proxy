package com.zj.demoproxy.annotation;

import java.lang.annotation.*;

/**
 * 允许扫描接口进行代理
 *
 * @author 19242
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Scan {

    String value() default "";
}
