package com.zj.demoproxy.function;

/**
 * 函数式接口
 * @author 19242
 */
@FunctionalInterface
public interface MethodFunction<T, U> {
    /**
     * 函数式接口
     * @param t 入参
     * @param u 注解
     * @param clz 返回类型
     * @return
     */
    Object apply(T t, U u, Class<?> clz);
}
