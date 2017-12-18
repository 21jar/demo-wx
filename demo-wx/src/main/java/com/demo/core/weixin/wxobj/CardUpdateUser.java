package com.demo.core.weixin.wxobj;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 更新用户通用卡
 *
 * @author hst on 2017/05/10
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class CardUpdateUser {

    private String code;

    @JsonProperty("card_id")
    @JSONField(name = "card_id")
    private String cardId;

    @JsonProperty("background_pic_url")
    @JSONField(name = "background_pic_url")
    private String backgroundPicUrl;

    private Integer bonus;

    @JsonProperty("record_bonus")
    @JSONField(name = "record_bonus")
    private String recordBonus;

    //需要设置的积分全量值
    private Integer balance;

    @JsonProperty("record_balance")
    @JSONField(name = "record_balance")
    private String recordBalance;

    @JsonProperty("custom_field_value1")
    @JSONField(name = "custom_field_value1")
    private String customFieldValue1;

    @JsonProperty("custom_field_value2")
    @JSONField(name = "custom_field_value2")
    private String customFieldValue2;

    @JsonProperty("custom_field_value3")
    @JSONField(name = "custom_field_value3")
    private String customFieldValue3;

    public CardUpdateUser(String code, String cardId) {
        this.code = code;
        this.cardId = cardId;
    }
}
