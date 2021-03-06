package com.demo.core.weixin.wxobj.result;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 创建带参二维码结果
 *
 * @author hst on 2017/01/06
 **/
@Getter
@Setter
@ToString(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class QrCodeResult extends BaseResult {

    private String ticket;

    @JsonProperty("expire_seconds")
    @JSONField(name = "expire_seconds")
    private String expireSeconds;

    private String url;

    public QrCodeResult(String errCode, String errMsg) {
        super(errCode, errMsg);
    }
}