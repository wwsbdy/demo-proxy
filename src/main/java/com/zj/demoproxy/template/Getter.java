package com.zj.demoproxy.template;

/**
 * @author 19242
 */
public interface Getter<T> {
    /**
     * 获取typeClz属性
     *
     * @return
     */
    Class<T> getTypeClz();

    /**
     * 获取dms地址
     *
     * @return
     */
    String getDmsUrl();
}
