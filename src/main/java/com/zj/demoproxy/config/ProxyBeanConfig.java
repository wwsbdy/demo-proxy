package com.zj.demoproxy.config;

import com.zj.demoproxy.pojo.AnnotationBeanMap;
import com.zj.demoproxy.pojo.StrategyBeanMap;
import com.zj.demoproxy.template.handler.BaseTemplateImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 19242
 */
@Configuration
public class ProxyBeanConfig {


    @Bean
    @ConditionalOnMissingBean
    public AnnotationBeanMap annotationBeanMap() {
        return new AnnotationBeanMap();
    }

    @Bean
    @ConditionalOnMissingBean
    public StrategyBeanMap strategyBeanMap(BaseTemplateImpl baseTemplateImpl){
        return new StrategyBeanMap(baseTemplateImpl);
    }
}
