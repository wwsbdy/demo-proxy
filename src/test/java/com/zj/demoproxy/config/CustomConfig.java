package com.zj.demoproxy.config;

import com.zj.demoproxy.annotation.Concat;
import com.zj.demoproxy.pojo.AnnotationBeanMap;
import com.zj.demoproxy.pojo.StrategyBeanMap;
import com.zj.demoproxy.service.BaseInterface;
import com.zj.demoproxy.service.impl.BaseInterfaceImpl;
import com.zj.demoproxy.template.handler.BaseTemplateImpl;
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
    AnnotationBeanMap annotationBean(){
        AnnotationBeanMap annotationBean = new AnnotationBeanMap();
        annotationBean.put(Concat.class, ((objects, annotation, clz) -> {
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

    @Bean
    public StrategyBeanMap strategyBeanMap(BaseTemplateImpl baseTemplateImpl, BaseInterfaceImpl baseInterface){
        StrategyBeanMap strategyBeanMap = new StrategyBeanMap(baseTemplateImpl);
        // 添加自定义父级接口
        strategyBeanMap.put(BaseInterface.class, baseInterface);
        return strategyBeanMap;
    }
}
