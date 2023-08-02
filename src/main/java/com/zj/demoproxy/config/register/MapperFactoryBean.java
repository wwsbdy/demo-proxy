package com.zj.demoproxy.config.register;


import com.zj.demoproxy.interfaces.BeanCreateRule;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 生产bean对象
 * @param <T>
 */
public class MapperFactoryBean<T> implements FactoryBean<T> {

    private Class<T> mapperInterface;
    @Autowired
    private BeanCreateRule beanCreateRule;

    MapperFactoryBean(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    @Override
    public T getObject() {
        Class<T> clazz = getObjectType();
        return beanCreateRule.create(clazz);
    }

    @Override
    public Class<T> getObjectType() {
        return this.mapperInterface;
    }

}

