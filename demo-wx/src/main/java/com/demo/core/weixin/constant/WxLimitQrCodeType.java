package com.demo.core.weixin.constant;

import lombok.Getter;

/**
 * 自定义的微信永久二维码的类型
 *
 * @author hst on 2017/01/09
 **/
@Getter
public enum WxLimitQrCodeType {
    STORE_QR_CODE(1);

    public static final String SPLIT = "_";

    private int limitType;

    WxLimitQrCodeType(int limitType) {
        this.limitType = limitType;
    }

    public static boolean withType(int type) {
        for (WxLimitQrCodeType limitQrCodeType : WxLimitQrCodeType.values()) {
            if (limitQrCodeType.limitType == type) {
                return true;
            }
        }
        return false;
    }
}
