package com.zj.demoproxy.interfaces;

/**
 * bean生成规则
 * @author 19242
 */
public interface BeanCreateRule {
    /**
     * 创建bean规则
     * @param clz
     * @param <T>
     * @return
     * @throws NoSuchMethodException
     */
    <T> T create(Class<T> clz);
}
