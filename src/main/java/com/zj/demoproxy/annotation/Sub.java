package com.zj.demoproxy.annotation;

import java.lang.annotation.*;

/**
 * 入参相减
 * @author 19242
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Sub {
}
