package com.zj.demoproxy.utils;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author 19242
 */
public class DecimalUtil {

    /**
     * 对象转换为BigDecimal
     * @param o 原数
     * @return
     */
    public static BigDecimal toDecimal(Object o){
        if (Objects.isNull(o)){
            return BigDecimal.ZERO;
        }
        if (o instanceof BigDecimal){
            return (BigDecimal) o;
        }
        if (o instanceof String || o instanceof Number){
            try {
                return new BigDecimal(String.valueOf(o));
            }catch (Exception e){
                return BigDecimal.ZERO;
            }
        }
        return BigDecimal.ZERO;
    }

    /**
     * BigDecimal转数字
     * @param decimal 原数
     * @param clz 转换类型
     * @return
     */
    public static Object toNumber(BigDecimal decimal, Class<?> clz) {
        if (Objects.isNull(decimal)){
            return null;
        }
        if (BigDecimal.class == clz){
            return decimal;
        }
        if (String.class == clz){
            try {
                return decimal.toString();
            }catch (Exception e){
                return null;
            }
        }
        if (int.class == clz || Integer.class == clz){
            try {
                return decimal.intValue();
            }catch (Exception e){
                return null;
            }
        }
        if (long.class == clz || Long.class == clz){
            try {
                return decimal.longValue();
            }catch (Exception e){
                return null;
            }
        }
        if (double.class == clz || Double.class == clz){
            try {
                return decimal.doubleValue();
            }catch (Exception e){
                return null;
            }
        }
        if (float.class == clz || Float.class == clz){
            try {
                return decimal.floatValue();
            }catch (Exception e){
                return null;
            }
        }
        return null;
    }
}
