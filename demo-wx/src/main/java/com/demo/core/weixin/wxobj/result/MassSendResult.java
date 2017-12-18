package com.demo.core.weixin.wxobj.result;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 高级群发接口结果
 *
 * @author hst on 2017/03/07
 **/
@Getter
@Setter
@ToString(callSuper = true)
public class MassSendResult extends BaseResult {

    @JSONField(name = "msg_id")
    @JsonProperty("msg_id")
    private String msgId;

    @JSONField(name = "msg_data_id")
    @JsonProperty("msg_data_id")
    private String msgDataId;

}
