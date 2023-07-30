package com.zj.demoproxy.enums;

import com.zj.demoproxy.annotation.Sout;
import com.zj.demoproxy.annotation.Sub;
import com.zj.demoproxy.function.MethodFunction;
import com.zj.demoproxy.utils.DecimalUtil;

import java.lang.annotation.Annotation;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

/**
 * 系统提供的基本注解
 *
 * @author 19242
 */
public enum CustomAnnotationEnum {
    /**
     * 打印sout的value
     */
    SOUT(Sout.class, (objects, an, clz) -> {
        Sout sout = (Sout) an;
        System.out.println(sout.value());
        return null;
    }),
    /**
     * 入参相减
     */
    SUB(Sub.class, (objects, annotation1, clz) -> {
        BigDecimal result = BigDecimal.ZERO;
        int size;
        if (Objects.nonNull(objects) && (size = objects.length) != 0){
            result = DecimalUtil.toDecimal(objects[0]);
            for (int i = 1; i < size; i++) {
                result = result.subtract(DecimalUtil.toDecimal(objects[i]));
            }
        }
        return DecimalUtil.toNumber(result, clz);
    })


    ;

    private final Class<? extends Annotation> annotation;

    private final MethodFunction<Object[], Annotation> func;

    CustomAnnotationEnum(Class<? extends Annotation> annotation, MethodFunction<Object[], Annotation> func) {
        this.annotation = annotation;
        this.func = func;
    }

    /**
     * 赋值基本注解方法
     * @param funcMap
     */
    public static void setValue(Map<Class<? extends Annotation>, MethodFunction<Object[], Annotation>> funcMap){
        for (CustomAnnotationEnum anEnum : CustomAnnotationEnum.values()) {
            funcMap.put(anEnum.annotation, anEnum.func);
        }
    }
}
