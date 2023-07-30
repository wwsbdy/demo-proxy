package com.zj.demoproxy.pojo;

import com.zj.demoproxy.enums.CustomAnnotationEnum;
import com.zj.demoproxy.function.MethodFunction;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * 注解策略
 *
 * @author 19242
 */
public class AnnotationBeanMap {

    private final Map<Class<? extends Annotation>, MethodFunction<Object[], Annotation>> funcMap;

    public AnnotationBeanMap() {
        funcMap = new HashMap<>(8);
        // 初始化基础注解
        CustomAnnotationEnum.setValue(funcMap);
    }

    public MethodFunction<Object[], Annotation> put(Class<? extends Annotation> key, MethodFunction<Object[], Annotation> value){
        return funcMap.put(key, value);
    }

    public MethodFunction<Object[], Annotation> get(Class<? extends Annotation> key){
        return funcMap.get(key);
    }

    public MethodFunction<Object[], Annotation> getOrDefault(Class<? extends Annotation> key, MethodFunction<Object[], Annotation> defaultValue){
        return funcMap.getOrDefault(key, defaultValue);
    }
}
