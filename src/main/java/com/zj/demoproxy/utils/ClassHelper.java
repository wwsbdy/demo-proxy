package com.zj.demoproxy.utils;

import com.zj.demoproxy.annotation.Scan;
import com.zj.demoproxy.template.BaseTemplate;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author 19242
 */
@Component
public class ClassHelper {

    private final Environment environment;

    public ClassHelper(Environment environment) {
        this.environment = environment;
    }

    /**
     * 获取BaseTemplate上的泛型class
     *
     * @param clazz
     * @return
     */
    public static Class<?> getBaseTemplateTypeClass(Class<? extends BaseTemplate> clazz) {
        Type[] interfacesTypes = clazz.getGenericInterfaces();
        for (Type t : interfacesTypes) {
            if (t instanceof ParameterizedType) {
                Type[] genericType2 = ((ParameterizedType) t).getActualTypeArguments();
                for (Type t2 : genericType2) {
                    return (Class<?>) t2;
                }
            }

        }
        return Object.class;
    }

    /**
     * 读取配置文件获取dms路径
     *
     * @param clazz
     * @return
     */
    public String getDmsUrl(Class<? extends BaseTemplate> clazz) {
        Scan scan = clazz.getAnnotation(Scan.class);
        String property = environment.getProperty(scan.value());
        return property;
    }
}
