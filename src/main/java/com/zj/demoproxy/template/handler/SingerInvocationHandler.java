package com.zj.demoproxy.template.handler;

import com.zj.demoproxy.config.AnnotationBean;
import com.zj.demoproxy.function.Function;
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

    private final AnnotationBean annotationBean;

    public SingerInvocationHandler(AnnotationBean annotationBean) {
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
            Function<Object[], Annotation> func = annotationBean.getFuncMap().get(annotation.annotationType());
            if (Objects.nonNull(func)){
                return func.apply(args, annotation, method.getReturnType());
            }
        }
        throw new RuntimeException("未知方法");
    }

}
