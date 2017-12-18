package com.demo.core.weixin.wxobj.result;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 汽车卡创建结果
 *
 * @author hst
 **/
@Getter
@Setter
@ToString(callSuper = true)
public class CarCardCreateResult extends BaseResult {

    @JsonProperty("card_id")
    @JSONField(name = "card_id")
    private String cardId;

}
