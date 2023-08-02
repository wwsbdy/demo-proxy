package com.zj.demoproxy.config.register;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * 扫描器
 *
 * @author 19242
 */
public class Scanner extends ClassPathBeanDefinitionScanner {
    private Class<? extends Annotation> annotationClass;
    private Class<? extends FactoryBean<?>> factoryBeanClass;

    public Scanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public void setAnnotationClass(Class<? extends Annotation> annotationClass) {
        this.annotationClass = annotationClass;
    }

    public void setFactoryBeanClass(Class<? extends FactoryBean<?>> factoryBeanClass) {
        this.factoryBeanClass = factoryBeanClass;
    }

    public void registerFilters() {
        addIncludeFilter(new AnnotationTypeFilter(annotationClass));
        // 可以禁止使用@Component
        addExcludeFilter(new AnnotationTypeFilter(Component.class));
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        AnnotationMetadata metadata = beanDefinition.getMetadata();
        return metadata.isInterface() && metadata.isIndependent();
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
        for (BeanDefinitionHolder holder : beanDefinitionHolders) {
            AbstractBeanDefinition definition = (AbstractBeanDefinition) holder.getBeanDefinition();
            String beanClassName = definition.getBeanClassName();
            definition.setBeanClass(factoryBeanClass);
            // 从构造方法传入参数
            definition.getConstructorArgumentValues().addGenericArgumentValue(beanClassName);
        }
        //返回的是beandefinition
        return beanDefinitionHolders;
    }
}
