package com.zj.demoproxy.template.handler;

import com.zj.demoproxy.pojo.AnnotationBeanMap;
import com.zj.demoproxy.function.MethodFunction;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author 19242
 */
@Component
public class SingerInvocationHandler implements InvocationHandler {

    private final AnnotationBeanMap annotationBean;

    public SingerInvocationHandler(AnnotationBeanMap annotationBean) {
        this.annotationBean = annotationBean;
    }

    /**
     * 动态代理调用方法
     *
     * @param proxy  生成的代理对象
     * @param method 代理的方法
     * @param args   方法参数
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Annotation[] annotations = method.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            MethodFunction<Object[], Annotation> func = annotationBean.get(annotation.annotationType());
            if (Objects.nonNull(func)){
                return func.apply(args, annotation, method.getReturnType());
            }
        }
        throw new RuntimeException("未知方法");
    }

}
