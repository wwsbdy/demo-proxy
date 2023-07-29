package com.zj.demoproxy.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 19242
 */
@Configuration
public class AnnotationBeanConfig {


    @Bean
    @ConditionalOnMissingBean
    public AnnotationBean annotationBean() {
        AnnotationBean annotationBean = new AnnotationBean();
        return annotationBean;
    }
}
