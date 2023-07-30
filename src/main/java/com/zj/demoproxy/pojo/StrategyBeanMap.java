package com.zj.demoproxy.pojo;

import com.zj.demoproxy.template.BaseTemplate;
import com.zj.demoproxy.template.handler.BaseTemplateImpl;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.util.HashMap;
import java.util.Map;

/**
 * 注解策略
 *
 * @author 19242
 */
public class StrategyBeanMap {

    private final Map<Class<?>, Object> funcMap;

    public StrategyBeanMap(BaseTemplateImpl baseTemplateImpl) {
        funcMap = new HashMap<>(8);
        put(BaseTemplate.class, baseTemplateImpl);
    }

    public Object put(Class<?> key, Object value){
        if (key.isInterface()) {
            return funcMap.put(key, value);
        }
        return null;
    }

    public Object get(Class<?> key){
        return funcMap.get(key);
    }

    public Object getOrDefault(Class<?> key, Object defaultValue){
        return funcMap.getOrDefault(key, defaultValue);
    }

    public DynamicType.Builder.MethodDefinition.ReceiverTypeDefinition<?> forEach(DynamicType.Builder.MethodDefinition.ReceiverTypeDefinition<Object> receiverTypeDefinition) {
        DynamicType.Builder.MethodDefinition.ReceiverTypeDefinition<?> result = receiverTypeDefinition;
        for (Map.Entry<Class<?>, Object> entry : funcMap.entrySet()) {
            result =  result.method(ElementMatchers.isDeclaredBy(entry.getKey())).intercept(MethodDelegation.to(entry.getValue()));
        }
        return result;
    }
}
