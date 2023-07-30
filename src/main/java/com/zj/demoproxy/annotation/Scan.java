package com.zj.demoproxy.annotation;

import java.lang.annotation.*;

/**
 * 允许代理接口类
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
