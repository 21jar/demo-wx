package com.demo.core.weixin.constant;

import lombok.Getter;

/**
 * 汽车卡常量
 *
 * @author hst on 2017/04/28
 **/

public class CarCardConstant {

    @Getter
    public enum DateInfoType {
        //支持固定时长有效类型
        DATE_TYPE_FIX_TERM_RANGE("DATE_TYPE_FIX_TERM_RANGE"),
        //固定日期有效类型
        DATE_TYPE_FIX_TERM("DATE_TYPE_FIX_TERM"),
        //永久有效类型
        DATE_TYPE_PERMANENT("DATE_TYPE_PERMANENT"),
        ;
        private String type;

        DateInfoType(String type) {
            this.type = type;
        }
    }

}
