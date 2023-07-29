package com.zj.demoproxy.template.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author 19242
 */
public class SingerInvocationHandler implements InvocationHandler {

    /**
     * 动态代理调用方法
     *
     * @param proxy  生成的代理对象
     * @param method 代理的方法
     * @param args   方法参数
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }

}
