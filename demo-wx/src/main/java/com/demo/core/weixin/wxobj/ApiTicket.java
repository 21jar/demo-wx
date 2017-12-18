package com.demo.core.weixin.wxobj;

import com.alibaba.fastjson.annotation.JSONField;
import com.demo.core.weixin.wxobj.result.BaseResult;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 调用微信JS接口的临时票据
 *
 * @author hst on 2017/03/17
 **/
@Getter
@Setter
@ToString
public class ApiTicket extends BaseResult {

    private String ticket;

    @JsonProperty("expires_in")
    @JSONField(name = "expires_in")
    private int expiresIn;
}
