package com.zj.demoproxy.template;

import java.util.List;
import java.util.function.Supplier;

/**
 * 基础模板
 *
 * @author 19242
 */
public interface BaseTemplate<T> extends Getter<T> {
    /**
     * 返回size个T
     *
     * @param size
     * @param func 创建规则
     * @return
     */
    List<T> getList(int size, Supplier<T> func);

    /**
     * json转T
     *
     * @param jsonStr
     * @return
     */
    T toObject(String jsonStr);
}
