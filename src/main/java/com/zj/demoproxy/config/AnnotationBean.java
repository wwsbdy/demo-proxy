package com.zj.demoproxy.config;

import com.zj.demoproxy.enums.CustomAnnotationEnum;
import com.zj.demoproxy.function.Function;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * 注解策略
 *
 * @author 19242
 */
public class AnnotationBean {
    private final Map<Class<? extends Annotation>, Function<Object[], Annotation>> funcMap;

    public AnnotationBean() {
        funcMap = new HashMap<>(8);
        // 初始化基础注解
        CustomAnnotationEnum.setValue(funcMap);
    }

    public Map<Class<? extends Annotation>, Function<Object[], Annotation>> getFuncMap() {
        return funcMap;
    }
}
