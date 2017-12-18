package com.demo.core.weixin.wxobj;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 汽车卡激活
 *
 * @author hst on 2017/05/02
 **/
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class CarCardActivate {

    @JsonProperty("init_bonus")
    @JSONField(name = "init_bonus")
    private Integer initBonus;

    @JsonProperty("init_balance")
    @JSONField(name = "init_balance")
    private Integer initBalance;

    @JsonProperty("activate_begin_time")
    @JSONField(name = "activate_begin_time")
    private Integer activateBeginTime;

    @JsonProperty("activateEndTime")
    @JSONField(name = "activateEndTime")
    private Integer activate_end_time;

    @JsonProperty("card_number")
    @JSONField(name = "card_number")
    private String cardNumber;

    private String code;

    @JsonProperty("card_id")
    @JSONField(name = "card_id")
    private String cardId;

    @JsonProperty("background_pic_url")
    @JSONField(name = "background_pic_url")
    private String backgroundPicUrl;

    @JsonProperty("init_custom_field_value1")
    @JSONField(name = "init_custom_field_value1")
    private String initCustomFieldValue1;

    @JsonProperty("init_custom_field_value2")
    @JSONField(name = "init_custom_field_value2")
    private String initCustomFieldValue2;

    @JsonProperty("init_custom_field_value3")
    @JSONField(name = "init_custom_field_value3")
    private String initCustomFieldValue3;

    public CarCardActivate(String cardNumber, String code, String cardId) {
        this.cardNumber = cardNumber;
        this.code = code;
        this.cardId = cardId;
    }
}
