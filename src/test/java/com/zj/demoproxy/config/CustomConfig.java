package com.zj.demoproxy.config;

import com.zj.demoproxy.annotation.Concat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义配置
 */
@Configuration
public class CustomConfig {
    /**
     * 自定义@Concat注解配置
     * @return
     */
    @Bean
    AnnotationBean annotationBean(){
        AnnotationBean annotationBean = new AnnotationBean();
        annotationBean.getFuncMap().put(Concat.class, ((objects, annotation, clz) -> {
            if (String.class != clz){
                throw new  RuntimeException("@Concat修饰的方法返回值必须是String类型");
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (Object object : objects) {
                stringBuilder.append(object);
            }
            return stringBuilder.toString();
        }));
        return annotationBean;
    }
}
