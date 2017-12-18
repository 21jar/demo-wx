package com.demo.core.utils;

import java.util.UUID;

/**
 * Created by 胡超云 on 2016/11/20.
 */
public class UuidUtil {
    /**
     * 获得一个UUID
     *
     * @return String UUID
     */
    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        //去掉“-”符号
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
    }
}
