package com.zj.demoproxy.config;

import com.zj.demoproxy.interfaces.BeanCreateRule;
import com.zj.demoproxy.pojo.AnnotationBeanMap;
import com.zj.demoproxy.template.handler.SingerInvocationHandler;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.NamingStrategy;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import net.bytebuddy.matcher.ElementMatchers;
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
    public BeanCreateRule beanCreateRule(SingerInvocationHandler singerInvocationHandler){
        return new BeanCreateRule() {
            @Override
            public <T> T create(Class<T> clazz) {
                // 使用byte-buddy动态代理接口
                Object o = null;
                try {
                    o = new ByteBuddy().with(new NamingStrategy.AbstractBase() {
                                @Override
                                protected String name(TypeDescription typeDescription) {
                                    return "com.zj.demoproxy.template.service.impl." + clazz.getSimpleName() + "Impl";
                                }
                            })
                            .subclass(Object.class)
                            .implement(clazz)
                            // 自定义方法用自定义拦截
                            .method(ElementMatchers.isDeclaredBy(clazz))
                            .intercept(InvocationHandlerAdapter.of(singerInvocationHandler))
                            .make()
                            .load(ClassLoader.getSystemClassLoader())
                            .getLoaded()
                            .newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                return (T) o;
            }
        };
    }
}
