package com.demo.core.weixin.pojo;

import lombok.Data;

/**
 * 验证消息是否来自微信的参数
 *
 * @author hst on 2016/11/30
 */

@Data
public class WxMsgValidParams {

    private String signature;

    private String timestamp;

    private String nonce;

    private String echostr;
    //消息加密是的签名串
    private String msg_signature;
    //消息体加密方式
    //为空或raw则为铭文加密
    private String encrypt_type;

    public boolean isEncrypt() {
        return encrypt_type != null && !"raw".equals(encrypt_type);
    }
}
