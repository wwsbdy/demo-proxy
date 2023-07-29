package com.zj.demoproxy.service;

import com.zj.demoproxy.annotation.Concat;
import com.zj.demoproxy.annotation.Scan;
import com.zj.demoproxy.annotation.Sout;
import com.zj.demoproxy.annotation.Sub;
import com.zj.demoproxy.model.DmsTestDo;
import com.zj.demoproxy.template.BaseTemplate;

import java.math.BigDecimal;

/**
 * @author 19242
 */
@Scan("dms.zj-demo")
public interface DmsTestService extends BaseTemplate<DmsTestDo> {

    @Sout("打印soutSome")
    void soutSome();

    /**
     * 拼接a和c
     * Concat优先执行
     * @param a
     * @param b
     * @return
     */
    @Concat
    @Sout("concat")
    String concat(String a, String b);

    /**
     * 三个数相减
     * @param a
     * @param b
     * @param c
     * @return
     */
    @Sub
    BigDecimal sub(int a, long b, BigDecimal c);
}
