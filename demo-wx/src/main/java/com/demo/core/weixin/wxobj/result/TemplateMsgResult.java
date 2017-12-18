package com.demo.core.weixin.wxobj.result;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 发送模版消息的返回信息
 *
 * @author hst on 2016/12/01
 */
@Getter
@Setter
@ToString(callSuper = true)
public class TemplateMsgResult extends BaseResult {

    @JsonProperty("msgid")
    @JSONField(name = "msgid")
    private String msgId;
}