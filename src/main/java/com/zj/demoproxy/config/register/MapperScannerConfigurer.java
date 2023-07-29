package com.zj.demoproxy.config.register;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.annotation.Annotation;

public class MapperScannerConfigurer implements BeanDefinitionRegistryPostProcessor, InitializingBean, ApplicationContextAware, BeanNameAware {

    private String beanName;
    private ApplicationContext applicationContext;
    private String basePackage;
    private Class<? extends Annotation> annotationClass;
    private Class<? extends FactoryBean<?>> factoryBeanClass;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // TODO Auto-generated method stub

    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    public void setAnnotationClass(Class<? extends Annotation> annotationClass) {
        this.annotationClass = annotationClass;
    }

    public void setFactoryBeanClass(Class<? extends FactoryBean<?>> factoryBeanClass) {
        this.factoryBeanClass = factoryBeanClass;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub

    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        Scanner scanner = new Scanner(registry);
        scanner.setAnnotationClass(annotationClass);
        scanner.setFactoryBeanClass(factoryBeanClass);
        scanner.registerFilters();
        scanner.scan(basePackage);
    }
}

